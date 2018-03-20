package word;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.io.FileWriter;

public class WordCount {
	       
public static void main(String[] args) throws Exception {
		 //解析args数组中的参数
		 try {
			 if (args.length == 0) {                           //提示未输入参数
	           System.out.println("请输入参数");  
	           return;  }
		      String name =args[args.length-1];                //默认args数组中的最后一个参数为读取文件
			 //定义各个记录项
		      int Char = 0;  
			  int word = 0; 
			  int line = 0;
			  int emptyline = 0;   
			  int codeline = 0;
			  int explainline =0;
			  int flag = 0; 
			  String output="result.txt";
			  String except= null;
			  String next = System.getProperty("line.separator");      //将行分隔符传入str
			   for(int m=0;m<args.length-1;m=m+1){
			    	  
				  if(args[m].equals("-o")) {
					  output=args[m+1];  }
				  if(args[m].equals("-e")) {
					  
					  except=args[m+1];  }
				  }
			  FileInputStream fis = new FileInputStream(name);
			  BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			 
			  while(br.read()!=-1)        //read()=-1代表数据文件读取完毕
			  {
			   String s = br.readLine();//按行读取
			   Char += s.length();//字符个数就是字符长度
			   if (s.length()==0) emptyline++;
			   String[] aa=s.split(" |,");
			   
			   
			   line++;
			   if(line>1){Char++;}
			   for (int i = 0 ; i <aa.length ; i++ ) {
                      
				      if(!(aa[i] == null || aa[i].isEmpty()))
				      {word=word+1;
				       flag=flag+1;
				       
				       if(!(except==null||except.isEmpty()))
				       {  FileInputStream ff = new FileInputStream(except);
						  BufferedReader bc = new BufferedReader(new InputStreamReader(ff));
						  String ss = bc.readLine();
				    	   String[] bb=ss.split(" ");
				    	   for(int k=0;k<bb.length;k++)
				    	   {
				    		   if(aa[i].equals(bb[k])) {word--;}
				    	   }
				       }
				      }
				      if(aa[i].equals("//")) {explainline=explainline+1;}
				      
				    }
			   if(flag>1) codeline=codeline+1;
			   flag=0;
			  }
			      //每次输出中都会将输出文本文件清空，重新写入计算结果
			   FileWriter fw = new FileWriter(output);
			  for(int m=0;m<args.length-1;m++)
			  {   
				  if (args[m].equals("-c")){ fw.write(name+",字符数： "+Char); fw.write(next); }
				  if (args[m].equals("-w")){ fw.write(name+",单词数： "+word);fw.write(next); }
				  if (args[m].equals("-l")) {fw.write(name+",行数：    "+line);fw.write(next);}
				  if(args[m].equals("-a")) { fw.write(name+",代码行数：    "+codeline);fw.write(next);
			                                 fw.write(name+",空行数：    "+emptyline);fw.write(next);
			                                 fw.write(name+",注释行：    "+explainline);fw.write(next);
				                           }
			  }
			  fw.close();
			  br.close();//关闭文件流
			  
		 }
		 catch(FileNotFoundException e){
			 System.out.println("未找到文件，请输入正确文件名！");
		 }
	}

}
