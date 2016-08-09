// Generated code from Butter Knife. Do not modify!
package github.johnnysc.mayakoffskij;

import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class FoundPoemsAdapter_ViewBinding<T extends FoundPoemsAdapter> implements Unbinder {
  protected T target;

  public FoundPoemsAdapter_ViewBinding(T target, Finder finder, Object source) {
    this.target = target;

    target.nameTextView = finder.findRequiredViewAsType(source, R.id.name_view, "field 'nameTextView'", TextView.class);
    target.poemTextView = finder.findRequiredViewAsType(source, R.id.poem_view, "field 'poemTextView'", TextView.class);
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.nameTextView = null;
    target.poemTextView = null;

    this.target = null;
  }
}
