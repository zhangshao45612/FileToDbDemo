package com.zsj.filetodbdemo.util;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * <ul>
 * <li>{@link ListUtils#getSize(List)}</li>
 * <li>{@link ListUtils#isEmpty(List)}</li>
 * <li>{@link ListUtils#isEquals(List, List)}</li>
 * <li>{@link ListUtils#isEquals(Object, Object)}</li>
 * <li>{@link ListUtils#join(List)}</li>
 * <li>{@link ListUtils#join(List,char)}</li>
 * <li>{@link ListUtils#addDistinctEntry(List, Object)}</li>
 * <li>{@link ListUtils#addDistinctList(List, List)}</li>
 * <li>{@link ListUtils#addListNotNullValue(List, Object)}</li>
 * <li>{@link ListUtils#invertList(List)}</li>
 * <li>{@link ListUtils#randomList(List)}</li>
 * </ul>
 * 
 */
public class ListUtils {
    public static final String DEFAULT_JOIN_SEPARATOR = ",";
    private ListUtils( ){
    	throw new AssertionError( );
    }
    
    /**
     * get size of list
     * 
     * <pre>
     * getSize(null)   =   0;
     * getSize({})     =   0;
     * getSize({1})    =   1;
     * </pre>
     * 
     * @param <V>
     * @param sourceList
     * @return if list is null or empty, return 0, else return {@link List#size()}.
     */
    public static <V> int getSize(List<V> sourceList) {
    	if( isEmpty( sourceList ) ){
    		throw new IllegalArgumentException( "sourceList is illegal, please check it!" );
    	}
    	
        return sourceList == null ? 0 : sourceList.size();
    }

    /**
     * is null or its size is 0
     * 
     * <pre>
     * isEmpty(null)   =   true;
     * isEmpty({})     =   true;
     * isEmpty({1})    =   false;
     * </pre>
     * 
     * @param <V>
     * @param sourceList
     * @return if list is null or its size is 0, return true, else return false.
     */
    public static <V> boolean isEmpty(List<V> sourceList) {
        return (sourceList == null || sourceList.size() == 0);
    }

    /**
     * compare two list
     * 
     * <pre>
     * isEquals(null, null) = true;
     * isEquals(new List&lt;String&gt;(), null) = false;
     * isEquals(null, new List&lt;String&gt;()) = false;
     * isEquals(new List&lt;String&gt;(), new List&lt;String&gt;()) = true;
     * </pre>
     * 
     * @param <V>
     * @param actual
     * @param expected
     * @return true equal,false nonequal
     */
    public static <V> boolean isEquals(List<V> actual, List<V> expected) {
    	if( isEmpty( actual ) || isEmpty( expected )){
    		throw new IllegalArgumentException( "actual or expected is illegal, please check it!" );
    	}
    	
        if (actual.size() != expected.size()) {
            return false;
        }

        for (int i = 0; i < actual.size(); i++) {
        	System.out.println( "actual == " + actual.get(i) + " expected == " + expected.get(i) );
            if (!isEquals(actual.get(i), expected.get(i))) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * compare two object
     * 
     * @param actual
     * @param expected
     * @return <ul>
     *         <li>if both are null, return true</li>
     *         <li>return actual.{@link Object#equals(Object)}</li>
     *         </ul>
     */
    private static boolean isEquals(Object actual, Object expected) {
    	if( null == actual || null == expected ){
    		throw new IllegalArgumentException( "actual or expected is illegal, please check it!" );
    	}
    	
        return actual == expected || (actual == null ? expected == null : actual.equals(expected));
    }

    /**
     * join list to string, separator is ","
     * 
     * <pre>
     * join(null)      =   "";
     * join({})        =   "";
     * join({a,b})     =   "a,b";
     * </pre>
     * 
     * @param list
     * @return join list to string, separator is ",". if list is empty, return ""
     */
    public static String join(List<String> list) {
    	if( isEmpty( list ) ){
    		throw new IllegalArgumentException( "list is illegal, please check it!" );
    	}
    	
        return join(list, DEFAULT_JOIN_SEPARATOR);
    }

    /**
     * join list to string
     * 
     * <pre>
     * join(null, '#')     =   "";
     * join({}, '#')       =   "";
     * join({a,b,c}, ' ')  =   "abc";
     * join({a,b,c}, '#')  =   "a#b#c";
     * </pre>
     * 
     * @param list
     * @param separator
     * @return join list to string. if list is empty, return ""
     */
    public static String join(List<String> list, char separator) {
    	if( isEmpty( list ) ){
    		throw new IllegalArgumentException( "list is illegal, please check it!" );
    	}
    	
        return join(list, new String(new char[] {separator}));
    }

    /**
     * join list to string. if separator is null, use {@link #DEFAULT_JOIN_SEPARATOR}
     * 
     * <pre>
     * join(null, "#")     =   "";
     * join({}, "#$")      =   "";
     * join({a,b,c}, null) =   "a,b,c";
     * join({a,b,c}, "")   =   "abc";
     * join({a,b,c}, "#")  =   "a#b#c";
     * join({a,b,c}, "#$") =   "a#$b#$c";
     * </pre>
     * 
     * @param list
     * @param separator
     * @return join list to string with separator. if list is empty, return ""
     */
    public static String join(List<String> list, String separator) {
    	if( isEmpty( list ) || TextUtils.isEmpty( separator ) ){
    		throw new IllegalArgumentException( "list or separator is illegal, please check it!" );
    	}
    	
        if (separator == null) {
            separator = DEFAULT_JOIN_SEPARATOR;
        }

        StringBuilder joinStr = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            joinStr.append(list.get(i));
            if (i != list.size() - 1) {
                joinStr.append(separator);
            }
        }
        return joinStr.toString();
    }

    /**
     * add distinct entry to list
     * 
     * @param <V>
     * @param sourceList
     * @param entry
     * @return if entry already exist in sourceList, return false, else add it and return true.
     */
    public static <V> boolean addDistinctEntry(List<V> sourceList, V entry) {
    	if( isEmpty( sourceList ) || null == entry ){
    		throw new IllegalArgumentException( "sourceList or entry is illegal, please check it!" );
    	}
    	
        return (sourceList != null && !sourceList.contains(entry)) ? sourceList.add(entry) : false;
    }

    /**
     * add all distinct entry to list1 from list2
     * 
     * @param <V>
     * @param sourceList
     * @param entryList
     * @return the count of entries be added
     */
    public static <V> int addDistinctList(List<V> sourceList, List<V> entryList) {
        if( isEmpty( sourceList ) || isEmpty( entryList ) ){
    		throw new IllegalArgumentException( "sourceList or entryList is illegal, please check it!" );
    	}

        int sourceCount = sourceList.size();
        for (V entry : entryList) {
            if (!sourceList.contains(entry)) {
                sourceList.add(entry);
            }
        }
        return sourceList.size() - sourceCount;
    }

    /**
     * add not null entry to list
     * 
     * @param sourceList
     * @param value
     * @return <ul>
     *         <li>if sourceList is null, return false</li>
     *         <li>if value is null, return false</li>
     *         <li>return {@link List#add(Object)}</li>
     *         </ul>
     */
    public static <V> boolean addListNotNullValue(List<V> sourceList, V value) {
    	if( isEmpty( sourceList ) || null == value ){
    		throw new IllegalArgumentException( "sourceList or value is illegal, please check it!" );
    	}
    	
        return (sourceList != null && value != null) ? sourceList.add(value) : false;
    }

    /**
     * invert list
     * 
     * @param sourceList
     * @return random list
     */
    public static <V> List<V> invertList(List<V> sourceList) {
        if (isEmpty(sourceList)) {
            throw new IllegalArgumentException( "sourceList is illegal, please check it!" );
        }

        List<V> invertList = new ArrayList<V>(sourceList.size());
        for (int i = sourceList.size() - 1; i >= 0; i--) {
            invertList.add(sourceList.get(i));
        }
        
        return invertList;
    }
    
    /**
     * 打乱List
     * 
     * */
    public static <V> List<V> randomList(List<V> sourceList){
    	// Collections.shuffle( sourceList ); 可以用这个方法代替
    	if (ListUtils.isEmpty( sourceList )) {
    		throw new IllegalArgumentException( "sourceList is illegal, please check it!" );
        }
    	
    	List<V> randomList = new ArrayList<V>( sourceList.size( ) );
    	do{
    		int randomIndex = Math.abs( new Random( ).nextInt( sourceList.size() ) );
        	randomList.add( sourceList.remove( randomIndex ) );
    	}while( !ListUtils.isEmpty( sourceList ) );
    	
    	return randomList;
    }
}
