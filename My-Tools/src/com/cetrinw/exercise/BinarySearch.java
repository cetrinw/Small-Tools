package com.cetrinw.exercise;

/**
 * 二分查找,适用于有序序列快速查找
 * @author Cetrin Wang
 *
 */
public class BinarySearch {
	
	public static int rank(int key , int [] a){
		int ti = 0 ;
		int le = a.length-1;
		while(ti <= le){
			/*计算中间值，如果key<中间值,继续往左递归, ti不变, mid = (mid-1)/2; 
						  如果key > 中间值 , mid = mid +1 + (a.length-1-mid)/2;
						  直到匹配的key = a[mid],停止循环 
			*/
			int	mid = ti + (le-ti)/2;
			if		(key < a[mid]) le = mid -1;
			else if (key > a[mid]) ti = mid +1;
			else return mid;
		}
		return -1;
	}
	
	public static void main(String[] args) {
		int[] a = {1,2,3,4,5,6,7,8,9,10};
		
		int rank = rank(10,a);
		
		System.out.println(rank);
	}
}
