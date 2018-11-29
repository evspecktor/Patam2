package server;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class BestFirstSearch extends CommonSearcher implements Searcher{

	Searchable s;
	Set<State> closedSet= new HashSet<State>();
	ArrayList<State> solution = new ArrayList<State>();
		
	@Override
	public ArrayList<State> search(Searchable sbl) {
				
		this.openList.add(sbl.GetInitState());
		this.openList.peek().setCameFrom(null);
		this.openList.peek().setCost(0);
		ArrayList<State> successors;	
		State currentState;
		System.out.println("---Best First Search. Starting on : " + LocalDateTime.now());
		while(!openList.isEmpty())
			{
				currentState = openList.poll();
				closedSet.add(currentState);
				
				//if goal
				if(currentState.isGoal())
				{
					do
					{
						solution.add(currentState);
						currentState = currentState.getCameFrom();
					}while(currentState!=null);
					
					openList.clear();
					closedSet.clear();
					System.out.println("---Best First Search - End on: " + LocalDateTime.now());
					return solution;
				}
				
				//if not goal - get the children
				successors = sbl.getAllStates(currentState);
				for (State s : successors)
				{
					if (! (openList.contains(s) || closedSet.contains(s)))
						openList.add(s);
				}
			}
		return solution;		
	}
}
	
//
//	public ArrayList<State> search1(Searchable sbl) {
//		System.out.println("---Best First Search - StartMethod---");
//		this.openList.add(sbl.GetInitState());
//		this.openList.peek().setCameFrom(null);
//		this.openList.peek().setCost(0);
//			
//		State currentState;
//		System.out.println("---1 Best First Search starting on : ---" + LocalDateTime.now());
//		while(!openList.isEmpty())
//			{
//				//Arrays.sort(openList.toArray());
//				currentState = openList.poll();
//				closedSet.add(currentState);
//				System.out.println("---2 Best First Search --- scurrent state: " + currentState.state);
//				
//				//TODO - check if needed, if goal - add to the solution all the fathers 
//				if(currentState.isGoal())
//				{
//					System.out.println("---3 Best First Search - check if goal ---");
//					do
//					{
//						solution.add(currentState);
//						currentState = currentState.getCameFrom();
//					}while(currentState!=null);
//					
//					openList.clear();
//					closedSet.clear();
//					System.out.println("---Best First Search - EndMethod on: ---" + LocalDateTime.now());
//					return solution;
//				}
//				// if not goal - get all the children and check if they exist already
//				ArrayList<State> successors = new ArrayList<State>(sbl.getAllStates(currentState));
//				
//				//for each child
//				for (int i = 0; i < successors.size(); i++ )
//				{
//					// go over each state in the closed list 
//					for (State stateFromClosedList : closedSet)
//					{
//						//only if the child doesn't exist in closed list - check if exists in openList -
//						if (!(successors.get(i).equals(stateFromClosedList)))
//							if (openList.contains(successors.get(i)))
//							{
//								System.out.println("*************************the object is aleady in the openList*******************");
//							}
//							openList.add(successors.get(i));
//							//go over the open list 
////							if (!openList.isEmpty())
////							{
////								System.out.println("---BestFirstSearch--- : openListsize: " + openList.size());
////
////												
////							}
//					}
//				}
//		
//		System.out.println("---Best First Search - EndMethod on: ---" + LocalDateTime.now());
//			}
//		return solution;
//	}
//}
//
//	
////if (openList.contains(successors.get(i)))
////{
////	
////}
////for (State stateFromOpenList : openList)
////{
////	System.out.println("---BestFirstSearch--- : befor if");
////	//if child exists already - check the cost and update if cheaper
////	if (stateFromOpenList.equals(successors.get(i)))
////	{
////		System.out.println("---BestFirstSearch--- : if1: ");
////		if (successors.get(i).getCost() < stateFromOpenList.getCost())
////			stateFromOpenList.setCost(successors.get(i).getCost());
////	}
//////if child doesn't exist - add to openList
////	else
////	{
////		openList.add(successors.get(i));
////	}
////}
////}
//////if the open list is empty 
////else
////{
////openList.add(successors.get(i));
////}	