// Generated code from Butter Knife. Do not modify!
package github.johnnysc.mayakoffskij;

import android.view.View;
import android.widget.ListView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Finder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class FavoritePoems_ViewBinding<T extends FavoritePoems> implements Unbinder {
  protected T target;

  private View view2131492988;

  public FavoritePoems_ViewBinding(final T target, Finder finder, Object source) {
    this.target = target;

    View view;
    target.favoritesList = finder.findRequiredViewAsType(source, R.id.favoritesList, "field 'favoritesList'", ListView.class);
    view = finder.findRequiredView(source, R.id.clearFavorites, "method 'clearFavorites'");
    view2131492988 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.clearFavorites();
      }
    });
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.favoritesList = null;

    view2131492988.setOnClickListener(null);
    view2131492988 = null;

    this.target = null;
  }
}
