import java.util.*;
public class PageIndex{
	LinkedList<WordEntry> words= new LinkedList<WordEntry>();
	public void addPositionForWord(String str, Position p)
	{	str.toLowerCase();
		WordEntry x = new WordEntry(str);
		x.addPosition(p);
		for(int i = 0;i<words.size();i++)
		{
			if(words.get(i).mergeWord(x))
			{
				return;
			}
		}
		words.add(x);
	}
	public LinkedList<WordEntry> getWordEntries()
	{
		return words;
	}
	public void print()
	{
		for(int i =0 ; i< words.size();i++)
		{
			System.out.println(words.get(i).getWord());
		}
	}
}