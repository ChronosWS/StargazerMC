package chronosws.minecraft.ultracraft.common;

public enum BlockSide
{
  BOTTOM (0),
  TOP (1),
  NORTH (2),
  SOUTH (3),
  WEST (4),
  EAST (5);

  public final int sideIndex;
  
  private BlockSide(int sideIndex)
  {
    this.sideIndex = sideIndex;
  }
}
