
/**
 * Created by wen on 2017/2/18.
 */
 
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Main
{
	private static Document document;
	
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		readHtml(scanner.next());
		CompileUrl();
	}
	public static void readHtml(String musicUrl)
	{
		try
        {
            document = Jsoup.connect(musicUrl).get();
            //System.out.println(document);
			Elements elements=document.getElementsByClass("mod_content");
			Elements elements1=document.getElementsByClass("singer_say");
			System.out.println("正在解析...");
			System.out.println("歌曲名称:" + elements.select("h2.play_name").text());
			System.out.println("歌曲图片:" + elements.select("div.play_photo").select("img").attr("src"));
			System.out.println("歌手自述:" + elements1.select("p").text());
			//System.out.println("下载地址:"+document.select("script").get(2).data());
		}
        catch (IOException e)
        {}
	}
	//正则表达式，截取http://dl网址
	public static void CompileUrl()
	{
		Pattern pattern=Pattern.compile("(http://dl){1}[\\w\\&\\=\\?\\.\\-/:]+");
		Matcher matcher=pattern.matcher(document.select("script").get(2).data());
		while (matcher.find())
		{
			System.out.println("下载地址:" + matcher.group(0));
		}
	}
}
