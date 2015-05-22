package week4.toaster;

import com.nitido.utils.toaster.Toaster;

public class Toasterr {
    
       public static void main(String[] args) throws InterruptedException {
           Toaster toasterManager = new Toaster();
           Thread.sleep(15);
           toasterManager.showToaster("JToaster Hello World!");     
    }
}
