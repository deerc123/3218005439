package Yuxian;




 

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import java.util.Map;

import java.util.Set;

 

/**

 * 字符串相似性匹配算法

 * Created by panther on 15-7-20.

 */

public class CompareStrSimUtil {

	private static int compare(String str, String target, boolean isIgnore) {

		int d[][]; // 矩阵

		int n = str.length();

		int m = target.length();

		int i; // 遍历str的

		int j; // 遍历target的

		char ch1; // str的

		char ch2; // target的

		int temp; // 记录相同字符,在某个矩阵位置值的增量,不是0就是1

		if (n == 0) {

			return m;

		}

		if (m == 0) {

			return n;

		}

		d = new int[n + 1][m + 1];

		for (i = 0; i <= n; i++) { // 初始化第一列

			d[i][0] = i;

		}

 

		for (j = 0; j <= m; j++) { // 初始化第一行

			d[0][j] = j;

		}

 

		for (i = 1; i <= n; i++) { // 遍历str

			ch1 = str.charAt(i - 1);

			// 去匹配target

			for (j = 1; j <= m; j++) {

				ch2 = target.charAt(j - 1);

				if (isIgnore) {

					if (ch1 == ch2 || ch1 == ch2 + 32 || ch1 + 32 == ch2) {

						temp = 0;

					} else {

						temp = 1;

					}

				} else {

					if (ch1 == ch2) {

						temp = 0;

					} else {

						temp = 1;

					}

				}

 

				// 左边+1,上边+1, 左上角+temp取最小

				d[i][j] = min(d[i - 1][j] + 1, d[i][j - 1] + 1, d[i - 1][j - 1] + temp);

			}

		}

		return d[n][m];

	}

 

	private static int min(int one, int two, int three) {

		return (one = one < two ? one : two) < three ? one : three;

	}

 

	public static float getSimilarityRatio(String str, String target, boolean isIgnore) {

		float ret = 0;

		if (Math.max(str.length(), target.length()) == 0) {

			ret = 1;

		} else {

			ret = 1 - (float) compare(str, target, isIgnore) / Math.max(str.length(), target.length());

		}

		return ret;

	}
	public static StringBuilder replaceAll(StringBuilder stb, String oldStr, String newStr) {

		if (stb == null || oldStr == null || newStr == null || stb.length() == 0 || oldStr.length() == 0)

			return stb;

		int index = stb.indexOf(oldStr);

		if (index > -1 && !oldStr.equals(newStr)) {

			int lastIndex = 0;

			while (index > -1) {

				stb.replace(index, index + oldStr.length(), newStr);

				lastIndex = index + newStr.length();

				index = stb.indexOf(oldStr, lastIndex);

			}

		}

		return stb;

	}

 
    public static void main(String[] args) throws IOException {

    	File file1=new File("src/orig.txt");
        File file2=new File("src/orig_0.8_dis_10.txt");
        
    	BufferedReader f1 = new BufferedReader(new InputStreamReader(new FileInputStream(file1)));
		
        BufferedReader f2 = new BufferedReader(new InputStreamReader(new  FileInputStream(file2)));
        String  Str;
        StringBuilder Str1 = new StringBuilder();
        StringBuilder Str2 = new StringBuilder();
        
        while((Str = f1.readLine()) != null){//将文本转化为String
            Str1.append(Str).append("\r\n");
        }
        while ((Str = f2.readLine())!=null){
            Str2.append(Str).append("\r\n");
        }
        String Str3 = ""+Str1;
        String Str4 = ""+Str2;
        Str3 = Str3.replaceAll(" [\\p{P}\\p{S}\\p{Z}]", "");
        Str4 = Str4.replaceAll(" [\\p{P}\\p{S}\\p{Z}]", "");
        StringBuilder Str5 = new StringBuilder(Str3); 
        StringBuilder Str6 = new StringBuilder(Str4); 
        CompareStrSimUtil lt = new CompareStrSimUtil();
      //检测是否成功获取以上文本的绝对路径
        System.out.println("源文本绝对路径：" + file1.getAbsolutePath());
        System.out.println("查重文本绝对路径：" + file2.getAbsolutePath());
       
        System.out.println("similarityRatio=" + lt.getSimilarityRatio(Str5.toString(), Str6.toString(), true));

       
 }}

 



