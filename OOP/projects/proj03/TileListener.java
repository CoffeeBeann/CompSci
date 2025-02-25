/*************************************************
Filename: TileListener.java
Author: MIDN Ian Coffey (m261194)
Implement Tile Listener for Board Implementation
**************************************************/

public interface TileListener
{
    public void activated(Tile t);
    public void deactivated(Tile t);
}