package chronosws.minecraft.ultracraft.utilities;

import java.io.File;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

/**
 * Implements helper methods used to save and restore configurations from the file system.
 * 
 * @author Cliff
 *
 */
public class Config
{
  /**
   * Annotation for a config class specifying the name of the file to use.  The default is to 
   * use the class name itself.
   */
  @Retention(RetentionPolicy.RUNTIME)
  public static @interface CfgSource
  {
    public String file() default "";
  }

  /**
   * Annotation for a config field specifying the comment to be placed in the config file.
   * 
   * <p>Example:<p>
   * <code>
     @CfgDesc(desc="Does this and that")
     @CfgVal
     public static int itemId = 1000;
     </code>
   */
  @Retention(RetentionPolicy.RUNTIME)
  public static @interface CfgDesc
  {
    public String desc() default "";
  }

  /**
   * Annotation for a config field representing an item id.
   * 
   * <p>Example:<p>
   * <code>@CfgItem public static int itemId = 12000;</code>
   */
  @Retention(RetentionPolicy.RUNTIME)
  public static @interface CfgItem {};

  /**
   * Annotation for a config field representing a block id.

   * <p>Example:<p>
   * <code> @CfgBlock public static int blockId = 1500;</code>
   */
  @Retention(RetentionPolicy.RUNTIME)
  public static @interface CfgBlock {}

  /**
   * Annotation for a config field representing a general value.
   * <p>Example:<p>
   * <code>@CfgVal public static boolean boolVal = false;</code>
   */
  @Retention(RetentionPolicy.RUNTIME)
  public static @interface CfgVal {}
    
  /**
   * Synchronizes the config file specified settings with the provided config class.
   * 
   * <p>
   * This behavior results in values in the config class being overwritten with any which have
   * been provided in the configuration file.  The configuration may be modified and re-synced 
   * after initial load, but no changes which affect values already present in the file will be
   * saved.
   * 
   * @param baseConfigurationDirectory
   * The base directory for configs (/config in the Minecraft directory).
   * @param modId
   * The module ID.
   * @param configClass
   * The class with annotated fields which will be synchronized with the file representation.
   * @return The Configuration object representing the file version of the configuration.
   */
  public static Configuration sync(File baseConfigurationDirectory, String modId, Object configClass)
  {
    File configFile = getConfigFile(baseConfigurationDirectory, modId, configClass);
    
    Configuration config = new Configuration(configFile);      
    config.load();
    
    try
    {     
      Field[] fields = configClass.getClass().getFields();
      for (Field field : fields)
      {
        String configCategory = Configuration.CATEGORY_GENERAL;
        CfgDesc commentAnnotation = field.getAnnotation(CfgDesc.class);
        CfgBlock blockAnnotation = field.getAnnotation(CfgBlock.class);
        CfgItem itemAnnotation = field.getAnnotation(CfgItem.class);
        CfgVal valAnnotation = field.getAnnotation(CfgVal.class);
        
        if(blockAnnotation == null && itemAnnotation == null && valAnnotation == null)
        {
          continue;
        }
        
        if(blockAnnotation != null)
        {
          configCategory = Configuration.CATEGORY_BLOCK;
        }
        else if(itemAnnotation != null)
        {
          configCategory = Configuration.CATEGORY_ITEM;
        }
        
        //
        // Comment annotation
        //        
        String comment = null;
        if(commentAnnotation != null)
        {
          comment = commentAnnotation.desc();
        }

        //
        // Get property values
        //
        if(field.getType() == int.class)
        {
          int val = field.getInt(configClass);
          val = config.get(configCategory, field.getName(), val, comment).getInt(val);
          field.setInt(configClass, val);
        } 
        else if (field.getType() == boolean.class)
        {
          boolean val = field.getBoolean(configClass);
          val = config.get(configCategory, field.getName(), val, comment).getBoolean(val);
          field.setBoolean(configClass, val);
        }
        else if(field.getType() == double.class)
        {
          double val = field.getDouble(configClass);
          val = config.get(configCategory, field.getName(), val, comment).getDouble(val);
          field.setDouble(configClass, val);
        }
        else if(field.getType() == String.class)
        {
          String val = (String)field.get(configClass);
          val = config.get(configCategory, field.getName(), val, comment).getString();
          field.set(configClass, val);
        }
        else
        {
          // Should log this error
        }
      }
    }
    catch (Exception e)
    {
      // failed to load configs log
    }
    finally
    {
      config.save();
    }
    
    return config;
  }

  /**
   * Determines the full path to the configuration file for the specified mod and config class.
   * @param baseConfigurationDirectory
   * @param modId
   * @param configClass
   * @return The full path to the config file.
   */
  private static File getConfigFile(File baseConfigurationDirectory,
      String modId, Object configClass)
  {
    File modPath = new File(baseConfigurationDirectory, modId);
    File fullPath;
    
    CfgSource sourceAnnotation = configClass.getClass().getAnnotation(CfgSource.class);
    if(sourceAnnotation == null || sourceAnnotation.file().isEmpty())
    {
      fullPath = new File(modPath, configClass.getClass().getSimpleName() + ".cfg");
    }
    else     
    {
      fullPath = new File(modPath, sourceAnnotation.file() + ".cfg");
    }
    
    return fullPath;
  }
}
