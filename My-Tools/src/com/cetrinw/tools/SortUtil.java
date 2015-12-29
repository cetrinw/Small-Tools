package com.cetrinw.tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;

public final class SortUtil {

	//1.使用Collections.sort()排序
	public static ArrayList<Entry<String, Double>> sortDouble(HashMap<String, Double> map) {
		ArrayList<Entry<String, Double>> list = new ArrayList<Entry<String, Double>>(map.entrySet());
		Collections.sort(list, new Comparator<Entry<String, Double>>() {
			public int compare(Entry<String, Double> o1, Entry<String, Double> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});
		return list;
	}
	
	//使用Collections.sort()排序
	public static ArrayList<Entry<String, Integer>> sortInt(HashMap<String, Integer> map) {
		ArrayList<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(map.entrySet());
		Collections.sort(list, new Comparator<Entry<String, Integer>>() {
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});
		return list;
	}
	
	//2.自定义快速排序(降序)
	/*
	基本思想：选择一个基准元素,通常选择第一个元素或者最后一个元素,
			    通过一趟扫描，将待排序列分成两部分,一部分比基准元素小,一部分大于等于基准元素,
			    此时基准元素在其排好序后的正确位置,然后再用同样的方法递归地排序划分的两部分。
	注： 快速排序是不稳定的排序。
　　		快速排序的时间复杂度为O(nlogn)。
　　		当n较大时使用快排比较好，当序列基本有序时用快排反而不好。
	*/
	public static void myQuickSort(String[] array) {
		if(array.length > 0){
			quickSort(array,0,array.length-1);
		}
	}

	private static void quickSort(String[] array, int start, int end) {
		if(start < end){ //防止死循环
			int middle = getMiddle(array,start,end);
			quickSort(array, start, middle-1); //递归前半部分数据
			quickSort(array, middle+1, end); //递归后半部分数据
		}
	}

	private static int getMiddle(String[] array, int start, int end) {
		String temp = array[start]; //基础元素
		while(start < end){
			//找比基础元素小的位置
			while(start < end &&
//				Float.parseFloat(array[end].split(":")[1]) <= Float.parseFloat(temp.split(":")[1]))
				Double.parseDouble(array[end].split(":")[1]) <= Double.parseDouble(temp.split(":")[1]))
			{
				end --;
			}
			array[start] = array[end];
			//找比基础元素大的位置
			while(start < end &&
					Double.parseDouble(array[start].split(":")[1]) >= Double.parseDouble(temp.split(":")[1]))
			{
				start ++;
			}
			array[end] = array[start];
		}
		array[start] = temp;
		return start;
	}
	
	//3.自定义归并排序
	/*
		基本思想:归并（Merge）排序法是将两个（或两个以上）有序表合并成一个新的有序表，
				  即把待排序序列分为若干个子序列，每个子序列是有序的。然后再把有序子序列合并为整体有序序列。
		注： 归并排序是稳定的排序方法。
　　			归并排序的时间复杂度为O(nlogn)。
　　			速度仅次于快速排序，为稳定排序算法，一般用于对总体无序，但是各子项相对有序的数列。
	*/
	public static void mergeSort(String[] array, int start, int end) {
		if(start < end){
			//取中间位置
			int middle = (start + end)/2;
			//对左边进行递归
			mergeSort(array, start, middle);
			//对右边进行递归
			mergeSort(array, middle + 1, end);
			//合并
			merge(array, start, middle, end);
		}
	}

	private static void merge(String[] array, int start, int middle, int end) {
		int mid = middle + 1;
		String[] tempArr = new String[array.length];
		int third = start;
		int temp = start;
		
		//将两个数组中较小的数据放进新的数据
		while(start <= middle && mid <= end){
			if(Double.parseDouble(array[start].split(":")[1]) <= Double.parseDouble(array[mid].split(":")[1])){
				tempArr[third++] = array[start++];
			} else {
				tempArr[third++] = array[mid++];
			}
		}
		//将剩余的数据放入新的数组
		while(start <= middle){
			tempArr[third++] = array[start++];
		}
		while(mid <= end){
			tempArr[third++] = array[mid++];
		}
		//将中间数据复制回原数组
		while(temp <= end){
			array[temp] = tempArr[temp++];
		}
	}
	
	/**
	 * 冒泡排序
	 * 时间复杂度O(n) - O(m=n^2)
	 */
	public static void mpSort(int[] a){
		int temp = 0 ;
		for(int i = a.length -1 ; i >0 ; i--){
			for(int j = 0 ; j < i ; j++){
				if(a[j] > a[j+1]){
					temp = a[j+1]  ;
					a[j+1] = a[j];
					a[j] = temp;
				}
			}
		}
	}
	
	//测试
	public static void main(String[] args) {
		
		String[] array = new String[]{"某:0.1018653417","自燃:0.0991048361","仓库:0.0638438061","起火:0.0991048361","原告:0.0766125673","亚麻:0.2831566745"};
		int[] a = {4,45,6,900,234,32,123,99,31,12,4,7,0,5,1,6,9,0,32,5,235,6789,8,0,5,12,2112,4,12};
		
		//1.用Collections.sort()排序
		ArrayList<String> arrayList = new ArrayList<String>();
		for (int i = 0; i < array.length; i++) {
			arrayList.add(array[i]);
		}
		long begin1 = System.currentTimeMillis();
		HashMap<String, Double> map = new HashMap<String, Double>();
		for (String string : array) {
			map.put(string.split(":")[0], Double.parseDouble(string.split(":")[1]));
		}
		ArrayList<Entry<String, Double>> sortedList = SortUtil.sortDouble(map);
		long end1 = System.currentTimeMillis();
		System.out.println("1.使用Collections.sort()排序：");
		System.out.print("排序前：");
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
		System.out.println("排序后：" + sortedList);
		System.out.println("排序使用时间：" + (end1 - begin1) + "毫秒");
		System.out.println("===================================================================");
		
		
		//2.自定义快速排序
		System.out.println("2.使用自定义快速排序：");
		System.out.print("排序前：");
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
		long begin2 = System.currentTimeMillis();
		SortUtil.myQuickSort(array);
		long end2 = System.currentTimeMillis();
		System.out.print("排序后：");
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
		System.out.println("排序使用时间：" + (end2 - begin2) + "毫秒");
		System.out.println("===================================================================");
		
		
		//3.自定义归并排序
		String[] array1 = new String[]{"某:0.1018653417","自燃:0.0991048361","仓库:0.0638438061","起火:0.0991048361","原告:0.0766125673","亚麻:0.2831566745"};
		System.out.println("3.使用定义归并排序：");
		System.out.print("排序前：");
		for (int i = 0; i < array1.length; i++) {
			System.out.print(array1[i] + " ");
		}
		long begin3 = System.currentTimeMillis();
		System.out.println();
		SortUtil.mergeSort(array1,0,array1.length-1);
		long end3 = System.currentTimeMillis();
		System.out.print("排序后：");
		for (int i = 0; i < array1.length; i++) {
			System.out.print(array1[i] + " ");
		}
		System.out.println();
		System.out.println("排序使用时间：" + (end3 - begin3) + "毫秒");
		System.out.println("===================================================================");
		
		long begin4 = System.currentTimeMillis();
		System.out.println("4.使用冒泡排序：");
		System.out.print("排序前：");
		for(int i : a){
			System.out.print(i+" ");
		}
		mpSort(a);

		long end4 = System.currentTimeMillis();
		System.out.println("");
		System.out.print("排序后：");
		for(int i : a){
			System.out.print(i+" ");
		}
		System.out.println("");
		System.out.println("排序使用时间：" + (end4 - begin4) + "毫秒");
	}
	
}
