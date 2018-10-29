import java.util.*;
public class Position{
	public PageEntry page;
	public int index;
	public int treeindex;
	public Position(Position makeCopy)
	{
		page = makeCopy.getPageEntry();
		index = makeCopy.getWordIndex();
		treeindex = makeCopy.getTreeIndex();
		// System.out.print(makeCopy+" "+this);
	}
	public Position(PageEntry p,int wordIndex,int treeIndex)
	{
		page = p;
		index= wordIndex;
		treeindex=treeIndex;
	}
	public PageEntry getPageEntry()
	{
		return page;
	}
	public int getWordIndex()
	{
		return index;
	}
	public int getTreeIndex()
	{
		return treeindex;
	}
	public void print(){
		System.out.print("( "+ page.name()+", "+index+", "+treeindex+ " )" );
	}
}