package pdfviewer.pdfviewer;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.github.barteksc.pdfviewer.util.FitPolicy;
import com.shockwave.pdfium.PdfDocument;

import java.util.List;

public class pdf_viewer extends Activity implements OnPageChangeListener,OnLoadCompleteListener{
    private static final String TAG = pdf_viewer.class.getSimpleName();

    PDFView pdf_View;
    Integer pageNumber = 0;
    String pdfFileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
         String SAMPLE_FILE = "book_demo.pdf";


        pdf_View= (PDFView)findViewById(R.id.pdfView);
        displayFromAsset(SAMPLE_FILE);
    }

    private void displayFromAsset(String assetFileName) {
        pdfFileName = assetFileName;
        pdf_View.stopFling();
        pdf_View.setBackgroundColor(Color.BLACK);
        pdf_View.fromAsset(assetFileName)
                .defaultPage(pageNumber)
                .swipeHorizontal(true)
               // .onError(error)
                //.pageSnap(true)
                //.autoSpacing(true)
                //.pageFling(true)
                .enableSwipe(true)
                .onPageChange(this)
                .enableAntialiasing(true)
                .enableAnnotationRendering(true)
                .onLoad(this)
                .spacing(20)
                .enableAntialiasing(true)
                .pageFitPolicy(FitPolicy.BOTH)
                .scrollHandle(new DefaultScrollHandle(this))
                .load();
    }


    @Override
    public void onPageChanged(int page, int pageCount) {
        pageNumber = page;
        setTitle(String.format("%s %s / %s", pdfFileName, page + 1, pageCount));
    }


    @Override
    public void loadComplete(int nbPages) {
        PdfDocument.Meta meta = pdf_View.getDocumentMeta();
        printBookmarksTree(pdf_View.getTableOfContents(), "-");

    }

    public void printBookmarksTree(List<PdfDocument.Bookmark> tree, String sep) {
        for (PdfDocument.Bookmark b : tree) {

            Log.e(TAG, String.format("%s %s, p %d", sep, b.getTitle(), b.getPageIdx()));

            if (b.hasChildren()) {
                printBookmarksTree(b.getChildren(), sep + "-");
            }
        }
    }

}
