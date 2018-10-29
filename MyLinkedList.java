import java.util.*;
public class MyLinkedList<T>{
	LinkedList<T> list = new LinkedList<T>() ;
	public void createfromlist(LinkedList<T> x)
	{
		list = x;
	}
	public void add(T element)
	{	if(!list.contains(element))	
		list.add(element);
	}
	public void append(MyLinkedList<T> newList)
	{
		for(int i =0;i<newList.size();i++)
		{	if(!list.contains(newList.elementByIndex(i)))
			this.list.add(newList.elementByIndex(i));	
		}
	}
	public void replace(int index, T element)
	{
		 list.set(index, element);
	}
	public void remove(T element)
	{
		list.remove(element);
	}
	public void removeByIndex(int i)
	{
		list.remove(list.get(i));
	}
	public Boolean contains(T element)
	{
		return list.contains(element);
	}
	public int size()
	{
		return list.size();
	}
	public T elementByIndex(int i)
	{
		if(i < this.size())
		{
			return list.get(i);
		}
		return null;
	}
	public void print()
	{
		for(int i =0;i< size();i++)
		{
			System.out.print(list.get(i)+" ");
		}
	}
}