package chronosws.minecraft.ultracraft.utilities;

import java.io.File;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

public class Config
{
  @Retention(RetentionPolicy.RUNTIME)
  public static @interface CfgSource
  {
    public String file() default "";
  }

  @Retention(RetentionPolicy.RUNTIME)
  public static @interface CfgDesc
  {
    public String desc() default "";
  }
  
  @Retention(RetentionPolicy.RUNTIME)
  public static @interface CfgItem {};

  @Retention(RetentionPolicy.RUNTIME)
  public static @interface CfgBlock {}

  @Retention(RetentionPolicy.RUNTIME)
  public static @interface CfgVal {}
  
  // example use
  // public static @CfgItem int itemId = 12000;
  // public static @CfgBlock int blockId = 350;
  // public static @CfgVal boolean booleanConfig = false;
  // public static @CfgDesc(desc="Does this and that") @CfgVal int itemId = 1000;
  
  public static Configuration load(File baseConfigurationDirectory, String modId, Object configClass)
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
