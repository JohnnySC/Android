// Generated code from Butter Knife. Do not modify!
package github.johnnysc.mayakoffskij;

import android.widget.ListView;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class SearchResultsList_ViewBinding<T extends SearchResultsList> implements Unbinder {
  protected T target;

  public SearchResultsList_ViewBinding(T target, Finder finder, Object source) {
    this.target = target;

    target.searchListView = finder.findRequiredViewAsType(source, R.id.search_results, "field 'searchListView'", ListView.class);
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.searchListView = null;

    this.target = null;
  }
}
