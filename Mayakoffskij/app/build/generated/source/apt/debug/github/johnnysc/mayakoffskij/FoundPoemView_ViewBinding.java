// Generated code from Butter Knife. Do not modify!
package github.johnnysc.mayakoffskij;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Finder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class FoundPoemView_ViewBinding<T extends FoundPoemView> implements Unbinder {
  protected T target;

  private View view2131493009;

  public FoundPoemView_ViewBinding(final T target, Finder finder, Object source) {
    this.target = target;

    View view;
    target.aPoemView = finder.findRequiredViewAsType(source, R.id.aPoemTextView, "field 'aPoemView'", TextView.class);
    target.nextButton = finder.findRequiredViewAsType(source, R.id.nextButton, "field 'nextButton'", Button.class);
    target.previousButton = finder.findRequiredViewAsType(source, R.id.previousButton, "field 'previousButton'", Button.class);
    view = finder.findRequiredView(source, R.id.add_to_favorites, "method 'addToFavorites'");
    view2131493009 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.addToFavorites();
      }
    });
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.aPoemView = null;
    target.nextButton = null;
    target.previousButton = null;

    view2131493009.setOnClickListener(null);
    view2131493009 = null;

    this.target = null;
  }
}
