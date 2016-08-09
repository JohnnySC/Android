// Generated code from Butter Knife. Do not modify!
package github.johnnysc.mayakoffskij;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Finder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class MainActivity_ViewBinding<T extends MainActivity> implements Unbinder {
  protected T target;

  private View view2131492970;

  private View view2131492971;

  private View view2131492975;

  private View view2131492976;

  public MainActivity_ViewBinding(final T target, final Finder finder, Object source) {
    this.target = target;

    View view;
    target.searchText = finder.findRequiredViewAsType(source, R.id.searchText, "field 'searchText'", EditText.class);
    target.listView = finder.findRequiredViewAsType(source, R.id.poems_list, "field 'listView'", ListView.class);
    target.allPoemsCount = finder.findRequiredViewAsType(source, R.id.allPoemsCount, "field 'allPoemsCount'", TextView.class);
    target.fontExample = finder.findRequiredViewAsType(source, R.id.fontexample, "field 'fontExample'", TextView.class);
    view = finder.findRequiredView(source, R.id.searchButton, "method 'onButtonClick'");
    view2131492970 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onButtonClick(finder.<Button>castParam(p0, "doClick", 0, "onButtonClick", 0));
      }
    });
    view = finder.findRequiredView(source, R.id.gotoFavorites, "method 'onButtonClick'");
    view2131492971 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onButtonClick(finder.<Button>castParam(p0, "doClick", 0, "onButtonClick", 0));
      }
    });
    view = finder.findRequiredView(source, R.id.fontsizeminus, "method 'onButtonClick'");
    view2131492975 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onButtonClick(finder.<Button>castParam(p0, "doClick", 0, "onButtonClick", 0));
      }
    });
    view = finder.findRequiredView(source, R.id.fontsizeplus, "method 'onButtonClick'");
    view2131492976 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onButtonClick(finder.<Button>castParam(p0, "doClick", 0, "onButtonClick", 0));
      }
    });
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.searchText = null;
    target.listView = null;
    target.allPoemsCount = null;
    target.fontExample = null;

    view2131492970.setOnClickListener(null);
    view2131492970 = null;
    view2131492971.setOnClickListener(null);
    view2131492971 = null;
    view2131492975.setOnClickListener(null);
    view2131492975 = null;
    view2131492976.setOnClickListener(null);
    view2131492976 = null;

    this.target = null;
  }
}
