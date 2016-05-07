package github.johnnysc.mayakoffskij;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/*
 * Created by Hovhannes Asatryan (https://github.com/JohnnySC) on 05.05.16.
 */
public class PoemAdapter extends BaseAdapter {
    public static ArrayList<PoemRecord> poems = null;
    public PoemAdapter(){
        poems = new ArrayList<>();
        poems.add(new PoemRecord("Утро","Угрюмый дождь скосил глаза"));
        poems.add(new PoemRecord("Ночь","Багровый и белый отброшен и скомкан"));
        poems.add(new PoemRecord("Порт","Простыни вод под брюхом были."));
        poems.add(new PoemRecord("Из улицы в улицу","Улица. Лица у догов годов резче."));
        poems.add(new PoemRecord("В авто","«Какая замечательная ночь!»"));
        poems.add(new PoemRecord("Уличное","В шатрах, истертых ликов цвель где"));
        poems.add(new PoemRecord("Вывескам","Читайте железные книги!"));
        poems.add(new PoemRecord("За женщиной","Раздвинув локтем тумана дрожжи"));
        poems.add(new PoemRecord("Театры","Рассказ о взлезших на подмосток"));
        poems.add(new PoemRecord("Кое-что про Петербург","Слезают слезы с крыши в трубы"));
        poems.add(new PoemRecord("А вы могли бы?","Я сразу смазал карту будня"));
        poems.add(new PoemRecord("Я","По мостовой..."));
        poems.add(new PoemRecord("Несколько слов о моей жене","Морей неведомых далеким пляжем"));
        poems.add(new PoemRecord("Несколько слов о моей маме","У меня есть мама на васильковых обоях."));
        poems.add(new PoemRecord("Несколько слов обо мне самом","Я люблю смотреть, как умирают дети."));
        poems.add(new PoemRecord("От усталости","Земля!"));
        poems.add(new PoemRecord("Исчерпывающая картина весны","Листочки."));
        poems.add(new PoemRecord("Мы","Лезем земле под ресницами вылезших пальм"));
        poems.add(new PoemRecord("Шумики, шумы и шумищи","По эхам городов проносят шумы"));
        poems.add(new PoemRecord("Любовь","Девушка пугливо куталась в болото"));
        poems.add(new PoemRecord("Адище города","Адище города окна разбили"));
        poems.add(new PoemRecord("Ничего не понимают","Вошел к парикмахеру, сказал — спокойный:"));
        poems.add(new PoemRecord("Нате!","Через час отсюда в чистый переулок"));
        poems.add(new PoemRecord("А всё-таки","Улица провалилась, как нос сифилитика."));
        poems.add(new PoemRecord("Еще Петербург","В ушах обрывки теплого бала"));
        poems.add(new PoemRecord("Кофта фата","Я сошью себе черные штаны"));
        poems.add(new PoemRecord("Послушайте!","Послушайте! Ведь, если звезды зажигают"));
        poems.add(new PoemRecord("Война объявлена","«Вечернюю! Вечернюю! Вечернюю!"));
        poems.add(new PoemRecord("Мысли в призыв","Войне ли думать: «Некрасиво в шраме»?"));
        poems.add(new PoemRecord("Мама и убитый немцами вечер","По черным улицам белые матери"));
        poems.add(new PoemRecord("Скрипка и немножко нервно","Скрипка издергалась, упрашивая"));
        poems.add(new PoemRecord("Вам!","Вам, проживающим за оргией оргию"));
        poems.add(new PoemRecord("Я и Наполеон","Я живу на Большой Пресне"));
        poems.add(new PoemRecord("Гимн судье","По Красному морю плывут каторжане"));
        poems.add(new PoemRecord("Гимн ученому","Народонаселение всей империи"));
        poems.add(new PoemRecord("Военно-морская любовь","По морям, играя, носится"));
        poems.add(new PoemRecord("Гимн здоровью","Среди тонконогих, жидких кровью"));
        poems.add(new PoemRecord("Гимн критику","От страсти извозчика и разговорчивой прачки"));
        poems.add(new PoemRecord("Гимн обеду","Слава вам, идущие обедать миллионы!"));
        poems.add(new PoemRecord("Теплое слово кое-каким порокам","Ты, который трудишься, сапоги ли чистишь"));
        poems.add(new PoemRecord("Вот так я сделался собакой","Ну, это совершенно невыносимо!"));
        poems.add(new PoemRecord("Кое-что по поводу дирижера","В ресторане было от электричества рыжо́."));
        poems.add(new PoemRecord("Пустяк у Оки","Нежно говорил ей"));
        poems.add(new PoemRecord("Великолепные нелепости","Бросьте! Конечно, это не смерть."));
        poems.add(new PoemRecord("Гимн взятке","Пришли и славословим покорненько"));
        poems.add(new PoemRecord("Внимательное отношение к взяточникам","Неужели и о взятках писать поэтам!"));
        poems.add(new PoemRecord("Чудовищные похороны","Мрачные до черного вышли люди"));
        poems.add(new PoemRecord("Мое к этому отношение","Май ли уже расцвел над городом"));
        poems.add(new PoemRecord("Ко всему","Нет. Это неправда."));
        poems.add(new PoemRecord("Себе, любимому, посвящает эти строки автор","Четыре. Тяжелые, как удар"));
        poems.add(new PoemRecord("Последняя петербургская сказка","Стоит император Петр Великий"));
        poems.add(new PoemRecord("России","Вот иду я, заморский страус"));
        poems.add(new PoemRecord("Эй!","Мокрая, будто ее облизали"));
        poems.add(new PoemRecord("Издевательства","Павлиньим хвостом распущу фантазию в пестром цикле"));
        poems.add(new PoemRecord("Никчемное самоутешение","Мало извозчиков?"));
        poems.add(new PoemRecord("Надоело","Не высидел дома"));
        poems.add(new PoemRecord("Дешевая распродажа","Женщину ль опутываю в трогательный роман"));
        poems.add(new PoemRecord("Мрак","Склоняются долу солнцеподобные лики их"));
        poems.add(new PoemRecord("Лунная ночь","Будет луна"));
        poems.add(new PoemRecord("Следующий день","Вбежал"));
        poems.add(new PoemRecord("В. Я. Брюсову на память","Разбоя след затерян прочно"));
        poems.add(new PoemRecord("Хвои","Не надо. Не просите"));
        poems.add(new PoemRecord("Братья писатели","Очевидно, не привыкну"));
        poems.add(new PoemRecord("Революция","поэтохроника"));
        poems.add(new PoemRecord("Забывчивый Николай","«Уж сгною, скручу их уж я!»"));
        poems.add(new PoemRecord("* * *","Нетрудно, ландышами дыша"));
        poems.add(new PoemRecord("Сказка о красной шапочке","Жил был на свете кадет"));
        poems.add(new PoemRecord("Интернациональная басня","Петух однажды"));
        poems.add(new PoemRecord("К ответу!","Гремит и гремит войны барабан"));
        poems.add(new PoemRecord("* * *","Ешь ананасы, рябчиков жуй"));
        poems.add(new PoemRecord("Наш марш","Бейте в площади бунтов топот!"));
        poems.add(new PoemRecord("Ода революции","Тебе, освистанная"));
        poems.add(new PoemRecord("Весна","Город зимнее снял"));
        poems.add(new PoemRecord("Хорошее отношение к лошадям","Били копыта"));
        poems.add(new PoemRecord("Приказ по армии искусства","Канителят стариков бригады"));
        poems.add(new PoemRecord("Радоваться рано","Будущее ищем"));
        poems.add(new PoemRecord("Поэт рабочий","Орут поэту"));
        poems.add(new PoemRecord("Той стороне","Мы не вопль гениальничанья"));
        poems.add(new PoemRecord("Левый марш","Разворачивайтесь в марше!"));
        poems.add(new PoemRecord("Потрясающие факты","Небывалей не было у истории в аннале"));
        poems.add(new PoemRecord("С товарищеским приветом, Маяковский","Дралось некогда греков триста"));
        poems.add(new PoemRecord("Мы идем","Кто вы?"));
        poems.add(new PoemRecord("Владимир Ильич!","Я знаю — не герои"));
        poems.add(new PoemRecord("III Интернационал","Мы идем революционной лавой."));
        poems.add(new PoemRecord("Необычайное приключение бывшее с Владимиром Маяковским летом на даче","В сто сорок солнц закат пылал"));
        poems.add(new PoemRecord("Отношение к барышне","Этот вечер решал"));
        poems.add(new PoemRecord("Гейнеобразное","Молнию метнула глазами"));
        poems.add(new PoemRecord("Горе","Тщетно отчаянный ветер"));
        poems.add(new PoemRecord("* * *","Портсигар в траву ушел на треть"));
        poems.add(new PoemRecord("Всем Титам и Власам РСФСР","По хлебным пусть местам летит"));
        poems.add(new PoemRecord("Сказка для шахтёра-друга про шахтёрки, чуни и каменный уголь","Раз шахтеры шахты близ"));
        poems.add(new PoemRecord("Последняя страничка гражданской войны","Слава тебе, краснозвездный герой!"));
        poems.add(new PoemRecord("О дряни","Слава. Слава, Слава героям!!!"));
        poems.add(new PoemRecord("Неразбериха","Лубянская площадь"));
        poems.add(new PoemRecord("Два не совсем обычных случая","Ежедневно как вол жуя"));
        poems.add(new PoemRecord("Стихотворение о Мясницкой о бабе и о всероссийском масштабе","Сапоги почистить — 1 000 000."));
        poems.add(new PoemRecord("Приказ № 2 армии искусств","Это вам — упитанные баритоны"));
        poems.add(new PoemRecord("Облако в штанах (поэма)","Вашу мысль"));
        poems.add(new PoemRecord("Флейта-позвоночник (поэма)","За всех вас, которые нравились..."));
        poems.add(new PoemRecord("Война и мир (поэма)","Хорошо вам"));
        poems.add(new PoemRecord("Человек (поэма)","Звенящей болью любовь замоля"));
        poems.add(new PoemRecord("150 000 000 (поэма)","Кто спросит луну?"));
        poems.add(new PoemRecord("Прозаседавшиеся","Чуть ночь превратится в рассвет"));
        poems.add(new PoemRecord("Спросили раз меня: «Вы любите ли НЭП?»","Многие товарищи повесили нос"));
        poems.add(new PoemRecord("Сволочи!","Гвоздимые строками"));
        poems.add(new PoemRecord("Бюрократиада","Бульвар. Машина."));
        poems.add(new PoemRecord("Выждем","Видит Антанта"));
        poems.add(new PoemRecord("Моя речь на Генуэзской конференции","Не мне российская делегация вверена"));
        poems.add(new PoemRecord("Мой май","Всем, на улицы вышедшим"));
        poems.add(new PoemRecord("Kак работает республика демократическая?","Словно дети, просящие с медом ковригу"));
        poems.add(new PoemRecord("Баллада о доблестном Эмиле","Замри, народ! Любуйся, тих!"));
        poems.add(new PoemRecord("Стих резкий о рулетке и железке","Есть одно учреждение,"));
        poems.add(new PoemRecord("После изъятий","Известно: у меня и у бога"));
        poems.add(new PoemRecord("Давиду Штеренбергу","Милый Давид!"));
        poems.add(new PoemRecord("Германия","Германия — это тебе!"));
        poems.add(new PoemRecord("Париж","Обшаркан мильоном ног."));
        poems.add(new PoemRecord("На цепь!","Патронов не жалейте! Не жалейте пуль!"));
        poems.add(new PoemRecord("Товарищи! Разрешите мне поделиться впечатлениями о Париже и о Моне́","Я занимаюсь художеством"));
        poems.add(new PoemRecord("Пернатые","Перемириваются в мире"));
        poems.add(new PoemRecord("О поэтах","Что поэзия?! Пустяк"));
        poems.add(new PoemRecord("На земле мир. Во человецех благоволение","Радостный крик греми"));
        poems.add(new PoemRecord("О «фиасках», «апогеях» и других неведомых вещах","На съезде печати"));
        poems.add(new PoemRecord("Барабанная песня","Наш отец — завод"));
        poems.add(new PoemRecord("Телеграмма мусье Пуанкаре и Мильерану","Есть слова иностранные"));
        poems.add(new PoemRecord("Когда голод грыз прошлое лето, что делала власть Советов?","Все знают: в страшный год"));
        poems.add(new PoemRecord("Когда мы побеждали голодное лихо, что делал патриарх Тихон?","Тихон патриарх, прикрывши пузо рясой"));
        poems.add(new PoemRecord("О патриархе Тихоне.","Известно: царь, урядник да поп"));
        poems.add(new PoemRecord("Мы не верим!","Тенью истемня весенний день"));
        poems.add(new PoemRecord("Тресты","В Москве редкое место"));
        poems.add(new PoemRecord("Газетный день","Рабочий утром глазеет в газету"));
        poems.add(new PoemRecord("Строки охальные про вакханалии пасхальные","Известно: буржуй вовсю жрет"));
        poems.add(new PoemRecord("Крестьянин,— помни о 17-м апреля!","Об этом весть до старости древней"));
        poems.add(new PoemRecord("17 апреля","Мы о царском плене забыли"));
        poems.add(new PoemRecord("Наше воскресенье","Еще старухи молятся"));
        poems.add(new PoemRecord("Весенний вопрос","Страшное у меня горе"));

    }


    @Override
    public int getCount() {
        return poems.size();
    }

    @Override
    public PoemRecord getItem(int position) {
        return poems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.poem_list_item,parent, false);
        }

        PoemRecord poem = poems.get(position);

        TextView nameTextView = (TextView)convertView.findViewById(R.id.name_view);
        nameTextView.setText(poem.getName());

        TextView poemTextView = (TextView)convertView.findViewById(R.id.poem_view);
        poemTextView.setText(poem.getPoem());
        return convertView;

    }
}
