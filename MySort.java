// Write a Java class MySort to sort a list of Comparable objects. It must
// contain one method:
// – ArrayList<T> sortThisList(MySet<T> listOf-
// TEntries): Given a set of T objects, this method
// returns a sorted list of objects. The list is represented as Java’s
// ArrayList where the following relation holds: if a<b,
// sortedlist.get(a).getNumber() >= sortedlist.get(b).getNumber().
// You can implement any sorting algorithm that you want. Your
// SearchEngine class should use the MySort class to sort the set of
// pages on the basis of the relevance criteria.
import java.util.*;
public class MySort{
	public ArrayList<SearchResult> sortThisList(MySet<SearchResult> listOfTEntries)
	{
		ArrayList<SearchResult> sorted = new ArrayList<SearchResult>();
		for(SearchResult x : listOfTEntries.data)
		{
			sorted.add(x);
		}
		for(int i=0;i<sorted.size();i++)
		{	int index=i;
			for(int j=i+1;j<sorted.size();j++)
			{
				if(sorted.get(index).compareTo(sorted.get(j))<0)
				{
					index = j;
				}
			}
			if(index!=i)
			{SearchResult temp = sorted.get(i);
			sorted.set(i,sorted.get(index));
			sorted.set(index,temp);}
			// sorted.get(i).print();
		}
		return sorted;
	}
}