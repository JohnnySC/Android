package github.johnnysc.notesrealm;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Hovhannes Asatryan on 23.07.16.
 */
public class RealmManager {
    private Realm realm;

    public RealmManager(Context context) {
        realm = Realm.getInstance(new RealmConfiguration.Builder(context)
                    .name("notesRealm.realm")
                    .build());
    }

    public Realm getRealm() {
        return realm;
    }
}
