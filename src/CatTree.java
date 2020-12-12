import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException; 


public class CatTree implements Iterable<CatInfo>{
	public CatNode root;
	
	
	public CatTree(CatInfo c) {
		this.root = new CatNode(c);
	}

	private CatTree(CatNode c) {
		this.root = c;
	}


	public void addCat(CatInfo c)
	{
		this.root = root.addCat(new CatNode(c));
	}

	public void removeCat(CatInfo c)
	{
		this.root = root.removeCat(c);
	}

	public int mostSenior()
	{
		return root.mostSenior();
	}

	public int fluffiest() {
		return root.fluffiest();
	}

	public CatInfo fluffiestFromMonth(int month) {
		return root.fluffiestFromMonth(month);
	}

	public int hiredFromMonths(int monthMin, int monthMax) {
		return root.hiredFromMonths(monthMin, monthMax);
	}

	public int[] costPlanning(int nbMonths) {
		return root.costPlanning(nbMonths);
	}



	public Iterator<CatInfo> iterator()
	{
		return new CatTreeIterator();
	}


	class CatNode {

		CatInfo data;
		CatNode senior;
		CatNode same;
		CatNode junior;

		public CatNode(CatInfo data) {
			this.data = data;
			this.senior = null;
			this.same = null;
			this.junior = null;
		}

		public String toString() {
			String result = this.data.toString() + "\n";
			if (this.senior != null) {
				result += "more senior " + this.data.toString() + " :\n";
				result += this.senior.toString();
			}
			if (this.same != null) {
				result += "same seniority " + this.data.toString() + " :\n";
				result += this.same.toString();
			}
			if (this.junior != null) {
				result += "more junior " + this.data.toString() + " :\n";
				result += this.junior.toString();
			}
			return result;
		}


		public CatNode addCat(CatNode c) {
			CatNode current= this;
			if(c.data.monthHired < current.data.monthHired) {
				if(current.senior== null) {
					current.senior=c;
				}
				else {
					current.senior.addCat(c);
				}	
			}
			else if(c.data.monthHired > current.data.monthHired) {
				if(current.junior== null) {
					current.junior=c;
				}
				else {
					current.junior.addCat(c);
				}	
			}
			else {
				if (c.data.furThickness < current.data.furThickness) {

					if(current.same == null) {
						current.same= c;
					}
					else {
						current.same.addCat(c);
					}	
				}
				else { //if the months hired are the same 

					if(current.same == null) {
						CatInfo temp= current.data;
						current.data=c.data;
						c.data=temp;
						current.same=c;
					}
					else { // if you want to add in the middle
						CatNode tempNext= current.same;
						CatInfo temp= current.data;
						current.data=c.data;
						c.data=temp;
						current.same=c;
						c.same=tempNext;
					}
				}
			}
			

			
			//CatNode previous=null;

/*
			while(true) {

				if(c.data.monthHired < current.data.monthHired) {
					if(current.senior == null) {
						previous= current;
						current.senior= c;
						break;

					}

					else {
						previous=current;
						current=current.senior;
					}	
				}

				else if (c.data.monthHired > current.data.monthHired) {
					if(current.junior == null) {
						previous=current;
						current.junior= c;
						break;	
					}

					else {
						previous=current;
						current=current.junior;
					}
				}

				else { 			//if the months hired are same 
					
					if (c.data.furThickness <= current.data.furThickness) {
						if (current.same == null) {
							previous= current;
							current.same=c;
							break;
						}
						else {
							previous=current;
							current=current.same;
						}
					}
	///////////////////////////////////////////////////////////////////////////				
					else {		// if the c.furthickness is > current.furthickness
						if(current==root) {
						
							c.same=current;
							root= c;
							
							if(current.junior!=null) {
								c.junior= current.junior;
							}
							if(current.senior!=null) {
								c.senior= current.senior;
							}
							
							current.junior=null;
							current.senior=null;
							break;
						}
						
					
						
						else if(previous.senior== current) {
							previous.senior=c;
							c.same= current;
							
							if(current.junior!=null) {
								c.junior= current.junior;
							}
							if(current.senior!=null) {
								c.senior= current.senior;
							}
							
							current.junior=null;
							current.senior=null;
							break;
						}
						
						else if(previous.junior== current) {
							previous.junior=current;
							c.same= current;
							
							if(current.junior!=null) {
								c.junior= current.junior;
							}
							if(current.senior!=null) {
								c.senior= current.senior;
							}
							
							current.junior=null;
							current.senior=null;
							break;
						}
						
					}
				}
				


			}
*/

			return current;


		}
		
		public CatNode findMin() {
			CatNode current= this;
			if(current==null) return null;
			else if(current.junior==null) {System.out.println(current.data.name);return current;}
			else return current.junior.findMin();
			
		}
		


		public CatNode removeCat(CatInfo c) {
			CatNode current= this; 
			// if tree is empty
			//if c is a senior cat
			if (c.monthHired < current.data.monthHired) {
				// if senior cat is null
				if (current.senior == null) return current;
				else current.senior= current.senior.removeCat(c);
			}
			//if c is a junior cat
			else if (c.monthHired > current.data.monthHired) {
				if(current.junior == null) return current;
				else current.junior= current.junior.removeCat(c);
			}
			// if c is same meaning month hired is the same
			else if(c.monthHired==current.data.monthHired) {
				//if we find the cat we want to remove 
				if(c.equals(current.data)) {
					
					if ((current.same ==null) && (current.senior==null) && (current.junior==null)) {
						current=null;
						return current;
					}
					
					//Case 1
					else if(current.same != null) {
						
						if(current.same.same !=null)  {
							CatInfo temp= current.same.data;
							CatInfo tempNext= current.same.same.data;
							current.data= temp;
							current.same.data= tempNext;
							current.same.same= null;
						}

						if(current.same.same==null) {
							CatInfo temp= current.same.data;
							current.same=null;
							current.data=temp;
						}
					
						if(current.senior != null) current.senior= current.senior.removeCat(c);
						if(current.junior != null) current.junior= current.junior.removeCat(c);	

					}
					
					//Case 2
					else if((current.same==null) && (current.senior != null)) {
						
						CatNode temp = current.senior;
						current.senior=null;
						current.data= temp.data;
						
						if(temp.senior != null) {
							current.senior= temp.senior.removeCat(c);
						}
						
						if(temp.same != null) {
							current.same= temp.same.removeCat(c);
						}
						
						if(temp.junior != null) {
							
							if(temp.junior.data.monthHired < current.junior.data.monthHired) {
								CatNode Jun= current.junior;
								current.junior=temp.junior;
								temp.junior.junior=Jun;
							}
						}	
					}			
					//Case 3
					
					else if((current.same==null) && (current.senior==null)) {
						
						if(current.junior.junior !=null)  {
							CatInfo temp= current.junior.data;
							CatInfo tempNext= current.junior.junior.data;
							current.data= temp;
							current.junior.data= tempNext;
							current.junior.junior= null;
						}

						if(current.junior.junior==null) {
							CatInfo temp= current.junior.data;
							current.junior=null;
							current.data=temp;
						}

						if(current.junior != null) current.senior= current.senior.removeCat(c);
						if(current.senior != null) current.junior= current.junior.removeCat(c);	

						
					}
					
			
				}

				else if (current.same ==null) return current;
				else current.same= current.same.removeCat(c);	
			}

			return current;
		}


		public int mostSenior() {
			// ADD YOUR CODE HERE
			//CatNode current= this;
			if(this.senior != null) {
				return this.senior.data.monthHired;
			}
			else {
				return this.data.monthHired;
			}
		}
		
		private int traverse_tree(CatNode Root, int furThickness) {
			int fluffiest=furThickness;
			
			if (Root.senior != null) fluffiest = traverse_tree(Root.senior, fluffiest);
			//if(root.same!= null) fluffiest= traverse_tree(root.same,fluffiest);
			if (Root.junior != null) fluffiest = traverse_tree(Root.junior,fluffiest);
			if(Root.data.furThickness > fluffiest) {
				fluffiest= Root.data.furThickness; 
				System.out.println( Root.data.name + " thickness " + fluffiest);
			}
			return fluffiest;
		}
		
		public int fluffiest() {
			
			CatNode curr= this;
			int furThickness= this.data.furThickness;
			return traverse_tree(curr, furThickness);
		}
		
		private int traverse_tree_month(CatNode c, int numCats, int monthMin, int monthMax ) {
			
			int num=numCats;
			int min= monthMin;
			int max= monthMax;
			if (c.senior != null) num=traverse_tree_month(c.senior, num, min, max);
			if (c.same != null) num=traverse_tree_month(c.same, num, min, max);
			if (c.junior != null) num=traverse_tree_month(c.junior, num, min, max);
			if((c.data.monthHired >= monthMin) && (c.data.monthHired<= max)){
				//System.out.println(c.data.name);
				num++;
			}
			
			return num;
		}


		public int hiredFromMonths(int monthMin, int monthMax) {
			if(monthMin>monthMax) return 0;
			else {
				CatNode cur = this;
				int numCats=0;
				int min= monthMin;
				int max= monthMax;
				return traverse_tree_month(cur, numCats, min, max); // DON'T FORGET TO MODIFY THE RETURN IF NEED BE
			}
		}
		
		private CatInfo traverse_fluffy(CatNode Root, int m) {
			int month= m;
			
			if(Root.data.monthHired == month) return Root.data;
			else if(Root.data.monthHired < m) return traverse_fluffy(Root.junior,month);
			else return traverse_fluffy(Root.senior,month);
			
		}

		public CatInfo fluffiestFromMonth(int month) {
			CatNode cur= this;
			return traverse_fluffy(cur, month); // DON'T FORGET TO MODIFY THE RETURN IF NEED BE
		}

		public int[] costPlanning(int nbMonths) {
			// ADD YOUR CODE HERE
			int[] monthlyCost= new int[nbMonths];
			
			CatTree cTree= new CatTree(this);
			
			for(CatInfo i : cTree) {
				int MonthsToAppt = i.nextGroomingAppointment-243;
				if(MonthsToAppt < nbMonths) {
					monthlyCost[MonthsToAppt] += i.expectedGroomingCost;
				}		
			}
			return monthlyCost; // DON'T FORGET TO MODIFY THE RETURN IF NEED BE
		}

	}

	private class CatTreeIterator implements Iterator<CatInfo> {
		
		private ArrayList<CatInfo>  Cats = new ArrayList<CatInfo>();
		private int listSize;
		private int curIndex;
		
		private void traverse_tree_cats(CatNode Root, ArrayList<CatInfo> catList) {
			
			//ArrayList<CatInfo> catInfos= catsList;
			CatNode cur=Root;
			
			if (cur.senior != null)traverse_tree_cats(cur.senior, catList);
			if(cur.same!= null)  traverse_tree_cats(cur.same, catList);
			catList.add(cur.data);
			if (cur.junior != null) traverse_tree_cats(cur.junior, catList);
		}

		public CatTreeIterator() {
			//YOUR CODE GOES HERE
			traverse_tree_cats(root, Cats);
			listSize= Cats.size();
		}

		public CatInfo next(){
			//YOUR CODE GOES HERE
			return Cats.get(curIndex++);

		}

		public boolean hasNext() {
			//YOUR CODE GOES HERE
			return ((curIndex < listSize)); // DON'T FORGET TO MODIFY THE RETURN IF NEED BE
		}
	}

}

