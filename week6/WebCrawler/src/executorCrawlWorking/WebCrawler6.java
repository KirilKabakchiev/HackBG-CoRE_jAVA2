package executorCrawlWorking;


import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Madalin Ilie
 */
public class WebCrawler6 implements LinkHandler {

    private final Collection<String> visitedLinks = Collections.synchronizedSet(new HashSet<String>());
//    private final Collection<String> visitedLinks = Collections.synchronizedList(new ArrayList<String>());    
    private String url;
    public final ExecutorService execService;
   

    public WebCrawler6(String startingURL, int maxThreads) {
        this.url = startingURL;
        execService = Executors.newFixedThreadPool(maxThreads);
//        Runtime.getRuntime().addShutdownHook(new Thread() {
//            public void run() {
//            	execService.shutdown();
//                try {
//					if (!execService.awaitTermination(6, TimeUnit.SECONDS)) { //optional *
//					    System.out.println("Executor did not terminate in the specified time."); //optional *
//					    List<Runnable> droppedTasks = execService.shutdownNow(); //optional **
//					   System.out.println("Executor was abruptly shut down. " + droppedTasks.size() + " tasks will not be executed."); //optional **
//					}
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//            }
//        });
    }

    @Override
    public  void queueLink(String link) throws Exception {
        startNewThread(link);
    }

    @Override
    public int size() {
        return visitedLinks.size();
    }

    @Override
    public void addVisited(String s) {
        visitedLinks.add(s);
    }

    @Override
    public boolean visited(String s) {
        return visitedLinks.contains(s);
    }

    private synchronized void startNewThread(String link) throws Exception {
    	this.addVisited(link);
        execService.execute(new LinkFinder(link, this));
        System.out.println("executedby " + Thread.currentThread().getName());
        System.out.println(link);
    }

    private void startCrawling() throws Exception {
        startNewThread(this.url);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        new WebCrawler6("http://ebusiness.free.bg", 64).startCrawling(); //"http://ebusiness.free.bg"
    }
}