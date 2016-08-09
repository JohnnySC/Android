// Generated code from Butter Knife. Do not modify!
package github.johnnysc.mayakoffskij;

import android.widget.Button;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class PoemView_ViewBinding<T extends PoemView> implements Unbinder {
  protected T target;

  public PoemView_ViewBinding(T target, Finder finder, Object source) {
    this.target = target;

    target.aPoemView = finder.findRequiredViewAsType(source, R.id.aPoemTextView, "field 'aPoemView'", TextView.class);
    target.nextButton = finder.findRequiredViewAsType(source, R.id.nextButton, "field 'nextButton'", Button.class);
    target.previousButton = finder.findRequiredViewAsType(source, R.id.previousButton, "field 'previousButton'", Button.class);
    target.addToFavorites = finder.findRequiredViewAsType(source, R.id.add_to_favorites, "field 'addToFavorites'", Button.class);
    target.removeFromFavorites = finder.findRequiredViewAsType(source, R.id.remove_from_favorites, "field 'removeFromFavorites'", Button.class);
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.aPoemView = null;
    target.nextButton = null;
    target.previousButton = null;
    target.addToFavorites = null;
    target.removeFromFavorites = null;

    this.target = null;
  }
}
