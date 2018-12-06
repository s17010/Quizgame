package jp.ac.itcollege.s17010std.quizgame

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text
import java.util.*

class RandomNormal : AppCompatActivity() {

    private var countLabel: TextView? = null
    private var questionLabel: TextView? = null
    private var answerBtn1: Button? = null
    private var answerBtn2: Button? = null
    private var answerBtn3: Button? = null
    private var answerBtn4: Button? = null

    private var rightAnswer: String? = null
    private var genre: String? = null
    private var rightAnswerCount = 0
    private var quizCount = 1

    internal var quizArray = ArrayList<ArrayList<String>>()

    internal var quizData = arrayOf(
            //arrayOf("R問題文","正解","選択肢1","選択肢2","選択肢3")
            //s17012 quiz data
            arrayOf("TWICEのメンバーでないのは誰？","ヒョナ","ダヒョン","サナ","ジヒョ","TWICE"),
            arrayOf("TWICEの餅ゴリの名前は？","パク・ジニョン","ソン・ゴクウ","ピッコロ","織田信長","TWICE"),
            arrayOf("TWICEのデビュー曲は？","OOH-AHH하게","TT","もう一度キスしたかった","ミルクムナリ","TWICE"),
            arrayOf("TWICEの曲Likeyのりパッケージアルバムの曲はどれ","Merry&Happy","BDZ","TT","aitai","TWICE"),
            arrayOf("ツウィの身長は？","170㌢","149㌢","190㌢","3メートル","TWICE"),
            arrayOf("youtubeで公開されているTWICEの曲　TTの再生回数は？(2018/12/01現在)","4億1千万回","100回","50万回","5000万回","TWICE"),
            arrayOf("TWICEは何人グループか？","9人","48人","5億6千万人","5人","TWICE"),
            arrayOf("TWICEの日本人メンバーじゃないのはどれか","ナヨン","サナ","モモ","ミナ","TWICE"),
            arrayOf("TWICEのツウィの性別はどれか","女","男","オカマ","オナベ","TWICE"),
            arrayOf("TWICEは結成何年目か？(2018/12/01現在)","3年","13年","28年","1年","TWICE"),
            // s16019 quiz data
            arrayOf("ゲーム内の1日は現実世界でどれくらいの時間ですか","20分","30分","60分","1440分","まいんくらふと"),
            arrayOf("次のうち、かまどの燃料として利用できるアイテムはどれですか","弓","矢","糸","紙","まいんくらふと"),
            arrayOf("落下ダメージは高さ何ブロック以上から発生しますか","3.5","3","4","4.5","まいんくらふと"),
            arrayOf("羊のカラーバリエーションのうち、自然にスポーンしないのはどの色ですか?","緑色","桃色","黒色","茶色","まいんくらふと"),
            arrayOf("マインクラフトのシリーズ売り上げは","約1億5400万本","約1億7000万本","約1億本","約8300万","まいんくらふと"),
            arrayOf("ストレイがスポーンする可能性のあるバイオームは","雪のツンドラ","山岳","タイガ","キノコ島","まいんくらふと"),
            arrayOf("アイアンゴーレム、スノウゴーレムを召喚するのに必要な共通のブロックは","くり抜かれたカボチャ","カボチャ","鉄ブロック","雪ブロック","まいんくらふと"),
            arrayOf("ウィザーを召喚するのに必要なブロックは","ソウルサンド","ネザーラック","溶岩ブロック","黒曜石","まいんくらふと"),
            arrayOf("エンダーパールを投げると、稀にスポーンするモブは","エンダーマイト","エンダーマン","シュルカー","ヴェックス","まいんくらふと"),
            arrayOf("ヴェックスを召喚するモブは","エヴォーカー","ヴィンディケーター","イリュージョナー","ウィッチ","まいんくらふと"),
            //s17001 quiz data
            arrayOf("日本の首都は？","東京","千葉","滋賀","ラーメンズ","闇鍋問題"),
            arrayOf("モンスターボール、いく使ったらプレミアボールがもらえる？","10","3","5","20","闇鍋問題"),
            arrayOf("Linuxちょっとできる人はだれ？","リーナス・トーバルズ","ツヨポン・センセ","ジェームス・トパーズ","ホワイトボー・ドトペン","闇鍋問題"),
            arrayOf("赤は？","#FF0040","#0000FF","#00FF40","#0B3B17","闇鍋問題"),
            arrayOf("魚の仲間は？","さめ","イルカ","さかなクン","サカナクション","闇鍋問題"),
            arrayOf("千利休のお師匠さんのお師匠さんは？","一休さん","中大兄皇子","浦島太郎","桃太郎","闇鍋問題"),
            arrayOf("youtubeを外部サイトに埋め込む祭使用するHTMLタグは？","iframe","選択肢1","選択肢2","選択肢3","闇鍋問題"),
            arrayOf("炎の匂い、しみついて？","むせる","けむい","くさい","はきそう","闇鍋問題"),
            arrayOf("ドラクエ5の魔王は?","ミルドラース","りゅうおう","つよぽん","ゲマ","闇鍋問題"),
            //arrayOf("","正解","選択肢1","選択肢2","選択肢3"),
            //s17002 quiz data
            arrayOf("チームの出場人数が一番多いスポーツは?","サッカー","バスケ","野球","バレーボール","スポーツ"),
            arrayOf("石川佳純、張本智和は何のスポーツ選手でしょう？","卓球","野球","水泳","クリケット","スポーツ"),
            arrayOf("世界記録９秒５８を１００メトル走でだしたのは？","ボルト","ジャスティンガトリン","ベンジョンソン","カールルイス","スポーツ"),
            arrayOf("カーリングの競技を行うリンクの長さは何メートル？","４５メートル","60メートル","450メートル","600メートル","スポーツ"),
            arrayOf("駅伝でつなぐものといえば？","たすき","ぜっけん","きずな","バトン","スポーツ"),
            arrayOf("トライアスロンで最後にする競技は？","長距離走","水泳","自転車","走り幅跳び","スポーツ"),
            arrayOf("侍ジャパンはなんのスポーツ？","野球","バレーボール","卓球","バスケ","スポーツ"),
            arrayOf("eスポーツは何をするスポーツ？","TVゲーム","勉強","カルタ","腕立て伏せ","スポーツ"),
            arrayOf("漢字で羽球と各スポーツは？","バトミントン","卓球","バスケ","テニス","スポーツ"),
            arrayOf("アメリカで４大スポーツと言われているのは、野球、バスケ、アイスホッケー、残る一つは？","アメリカンフットボール","ボクシング","サッカー","水泳","スポーツ"),
            //s17003 quiz data
            arrayOf("東方projectの最高難易度、正しいものを選びなさい。","ルナティック","ナイトメア","モストダイ","ヘル","東方project"),
            arrayOf("東方projectの主人公の一人である、博麗霊夢の能力は何？","空を飛ぶ程度の能力","式神を操る程度の能力","魔法を使う程度の能力","境界を操る程度の能力","東方project"),
            arrayOf("東方projectのキャラの中で、（バカ）の愛称で親しまれているキャラクターは誰？","チルノ","ミスティア・ローレライ","上白沢慧音","八坂神奈子","東方project"),
            arrayOf("フランドール・スカーレットのテーマ曲は何？","U.N.オーエンは彼女なのか？","亡き王女のためのセプテット","妖魔夜行","ツェペシュの幼き末裔","東方project"),
            arrayOf("人形師アリス・マーガトロイドの人形の名前で正しくないものを選べ。","コッペリア","上海","蓬莱","ゴリアテ","東方project"),
            arrayOf("メルラン？","プリズムリバー","メランコリー","ナイトバグ","ホワイトロック","東方project"),
            arrayOf("紅魔館のメイド長を務める、月時計ルナダイヤルでお馴染みの人物は？","魂魄妖夢","紅美鈴","十六夜咲夜","小野塚小町","東方project"),
            arrayOf("東方Vocal、幽閉サテライトさんの曲を選びなさい。","感情ケミストリー","BadApple!","チルノのパーフェクト算数教室","彼女が一番少女なのか？","東方project"),
            arrayOf("東方原作者、ZUN氏は辛党で大の酒好きですが、特に好きなお酒は何でしょうか？","ビール","ワイン","日本酒","カクテル","東方project"),
            arrayOf("東方projectの舞台となっている場所の名前は？","幻想郷","烏森","尸魂界","羽生蛇村","東方project"),
            //s17004 quiz data
            arrayOf("イーブイの進化系ではないものはどれ？","サンダー","エーフィ","ニンフィア","グレイシア","ポケモン"),
            arrayOf("飛行タイプではないポケモンはどれ？","ハクリュー","ギャラドス","プテラ","カイリュー","ポケモン"),
            arrayOf("最終進化までにほのおタイプしか持ってないものはどれ？","ヒノアラシ","ポカブ","ヒコザル","ヒトカゲ","ポケモン"),
            arrayOf("メガシンカがないポケモンはどれ？","エンペルト","カイロス","スピア","タブンネ","ポケモン"),
            arrayOf("準伝説ポケモンはどれ？","クルセリア","アブソル","ゾロアーク","ルカリオ","ポケモン"),
            arrayOf("第１世代のポケモンが進化系ではないベイビーポケモンのはどれ？","ソーナノ","ゴンベ","ピンプク","ピィ","ポケモン"),
            arrayOf("第１世代は何番までいる？","151","100","135","107","ポケモン"),
            arrayOf("ポケモン赤・緑においてニョロゾとの交換によって入手できるポケモンはどれ？","ルージュラ","ニョロトノ","ニョロボン","ヤドキング","ポケモン"),
            arrayOf("初代ポケモンゲームにおいて通信プレイでゲット可能なポケモン","フーディン","カモネギ","バリヤード","ルージュラ","ポケモン"),
            arrayOf("シルフカンパニーが作ったポケモンはどれ？","ポリゴン","ミュウツー","コイル","ベトベター","ポケモン"),
            //s17005 quiz data
            arrayOf("この中でアプリをＤＬしなくてもできるゲームはどれか","グランブルーファンタジー","プリンセスコネクトＲｅＤｉｖｅ","アイドルマスターシンデレラガールズスターライトステージ","シャドウバース","サイゲアプリ"),
            arrayOf("この中でリリースされたのが一番新しいのはどれか","プリンセスコネクトＲｅＤｉｖｅ","グランブルーファンタジー","アイドルマスターシンデレラガールズスターライトステージ","シャドウバース","サイゲアプリ"),
            arrayOf("プリンセスコネクトＲｅＤｉｖｅのジャンルは何か","アニメＲＰＧ","スマホＲＰＧ","デジタルカードゲーム","リズムゲーム","サイゲアプリ"),
            arrayOf("グランブルーファンタジーを作っている会社の名前は","サイゲームス","サイバーエージェント","バンダイナムコゲームス","ミクシィ","サイゲアプリ"),
            arrayOf("アイドルマスターシンデレラガールズスターライトステージはとある会社とサイゲームスの共同制作である。とある会社とはどこか","バンダイナムコゲームス","ガンホー","ＫＯＮＡＭＩ","ＳＥＧＡ","サイゲアプリ"),
            arrayOf("シャドウバースはサイゲームスのあるゲームが元となって制作された。あるゲームとはどれか","神撃のバハムート","怪盗ロワイヤル","釣りスタ","ドラゴンコレクション","サイゲアプリ"),
            arrayOf("プリンセスコネクトＲｅｄｉｖｅはサービス停止した前作を元に作られた作品である。前作の名前は","プリンセスコネクト","プリンセスコネクト１","プリンセスコネクトＳ","プリンセスコネクトＧ","サイゲアプリ"),
            arrayOf("グランブルーファンタジーは多数のメディア展開をしているが、この中でしていないメディア展開はどれか","映画","ＴＶアニメ","キャラソンＣＤ","コミック","サイゲアプリ"),
            arrayOf("サイゲームスはある会社の子会社である。サイゲームスの親会社の名前は","サイバーエージェント","シーエー・モバイル","サイバーエージェント・ベンチャーズ","ＣｙｂｅｒＺ","サイゲアプリ"),
            arrayOf("この中で２０１８年１１月現在サービスを終了しているサイゲームスのゲームはどれか","ナイツオブグローリー","ドラガリアロスト","神撃のバハムート","リトルノア","サイゲアプリ"),
            //s17007 quiz data
            arrayOf("「機動戦士ガンダム」作中において、主人公のライバルであるシャア・アズナブルが搭乗しなかったMSがはどれか","ドム","ゲルググ","ズゴック","ザク","ロボ"),
            arrayOf("「機動戦士ガンダムSEED」作中において、ストライクが装備しなかったストライカーパックはどれか","I.W.S.P","エールストライカー","ソードストライカー","ランチャーストライカー","ロボ"),
            arrayOf("「機動戦士ガンダムSEED DESTINY」作中において、シリーズ第1作である「機動戦士ガンダム」に登場するMSをオマージュしたMSが登場しますが、次のうち登場しなかったMSはどれか","ゲルググ","ザク","グフ","ドム","ロボ"),
            arrayOf("「機動戦士ガンダム00」作中において、トランザムシステムを使用することがなかった機体は次のうちどれ","ガンダムデュナメス","ガンダムエクシア","ガンダムキュリオス","ガンダムヴァーチェ","ロボ"),
            arrayOf("「劇場版 機動戦士ガンダム00 -A wakening of the Trailblazer-」においてデカルト・シャーマンが搭乗するモビルアーマーは次のうちどれか","ガデラーザ","トリロバイト","レグナント","アルヴァトーレ","ロボ"),
            arrayOf("「超時空要塞マクロス」に登場する可変戦闘機「VF-1バルキリー」のデザインモチーフになった現実世界の戦闘機は次のうちどれか","F-14","F-15","Su-27","EF-2000","ロボ"),
            arrayOf("「マクロスF」における主人公の搭乗する機体で、現実世界の戦闘機であるSu-27とF-14をモチーフにデザインされた機体は次のうちどれか","VF-25","VF-171","VF-27","YF-29","ロボ"),
            arrayOf("「ブレイクブレイド」作中において主人公ライガットが搭乗する動力を搭乗者の魔力に依存しないゴゥレムは次のうちどれか","デルフィング","エルテーミス","ファブニル","ヒュケリオン","ロボ"),
            arrayOf("「ARMORED CORE for Answer」においてオープニングムービーに登場し、本編においてはカラードランク9のリンクスが搭乗するラインアーク所属のネクストは次のうちどれか","ホワイト・グリント","ステイシス","アンサング","ノブリス・オブリージュ","ロボ"),
            arrayOf("「フルメタル・パニック！」において主人公が最初に搭乗するラムダ・ドライバ搭載型ASは次のうちどれか","アーバレスト","コダール","ガーンズバック","ベヘモス","ロボ"),
            //s17009 quiz data
            arrayOf("国産二輪メーカーではないのはどれか","TRIUMPH","HONDA","YAMAHA","SUZUKI","タスゴン、おバイク"),
            arrayOf("公道で使用してはいけないタイヤはどれか","スリックタイヤ","スポーツタイヤ","ハイグリップタイヤ","ツーリングタイヤ","タスゴン、おバイク"),
            arrayOf("日本国内において二輪を運転する際に着用義務はあるのはどれか","ヘルメット","サングラス","グローブ","プロテクター","タスゴン、おバイク"),
            arrayOf("速度制限が無い区域がある高速道路の国はどこか","ドイツ","フランス","イギリス","スペイン","タスゴン、おバイク"),
            arrayOf("国産二輪メーカーはどれか","Kawasaki","SHOEI","Arai","Hyod","タスゴン、おバイク"),
            arrayOf("二輪のロードレースはどれか","MotoGP","F1","INDY500","WRC","タスゴン、おバイク"),
            arrayOf("HONDAの創立者は誰か","本田総一朗","川崎正蔵","山葉寅楠","鈴木道雄","タスゴン、おバイク"),
            arrayOf("SUZUKIのバイクではないのはどれか","YZF-R1","GSXR-1000R","GSX1100S KATANA","Bandit1250","タスゴン、おバイク"),
            arrayOf("ネイキッドタイプのバイクはどれか","CB400SF","ZX-10R","S1000RR","CBR-1000RR","タスゴン、おバイク"),
            arrayOf("普通自動二輪免許は何ccまで運転できるか","400cc","300cc","450cc","500cc","タスゴン、おバイク"),
            //s17010 quiz data
            arrayOf("曲名当て：♬栄光に向って走るあの列車に乗って行こう","TRAIN-TRAIN","Choo Choo TRAIN","TRYIN-TRYIN","TRAIN","音楽"),
            arrayOf("曲名当て：♬広い宇宙の数あるひとつ","小さな恋のうた","あなたに","琉球愛歌","矛盾の上に咲く花","音楽"),
            arrayOf("曲名当て：♬空を押し上げて手を伸ばす君五月のこと","ハナミズキ","シクラメン","ホウセンカ","ハナツバキ","音楽"),
            arrayOf("曲名当て：♬卒業してからもう3度目の春","未来予想図II","未来予想図I","未来予想図III","未来予想図IV","音楽"),
            arrayOf("曲名当て：♬雨を避けたロッカールームで君は少しうつむいて","RUNNER","BUTTER","PITCHER","CATCEER","音楽"),
            arrayOf("歌詞当て：♬さよならと言った君の(?)はわからないけど","気持ち","思い","考え","泣き顔","音楽"),
            arrayOf("歌詞当て：♬どんなに離れてても(?)はそばにいるわ","心","僕","私","思い","音楽"),
            arrayOf("歌詞当て：♬島唄よ風に乗り(?)とともに海を渡れ","鳥","声","涙","私","音楽"),
            arrayOf("歌詞当て：♬君と出逢ってからいくつもの(?)を語り明かした","夜","時代","日々","思い出","音楽"),
            arrayOf("\"歌詞当て：♬\"\"(?)\"\"の響きだけで強くなれる気がしたよ\"","愛してる","好きだよ","月がきれいですね","ラブソング","音楽"),
            //s17011 quiz data
            arrayOf("アニメ「アイドルマスターシンデレラガールズ」1話にて広告が掲示されている、毎週土曜21時から放送されていた白坂小梅が主演を務めるドラマのタイトルは次のうちどれ？","「Zombi Girl」","「328日後....」","「ミュンヘンゾンビ紀行」","「Beware Watch」","シンデレラガールズ"),
            arrayOf("アニメ「アイドルマスターシンデレラガールズ」1話にてCDショップに二宮飛鳥のポスターが掲示されていますが、このポスターで二宮飛鳥が着ている衣装は次のうちどれ？","[ｴｸｽﾃﾝﾄﾞﾜｰﾙﾄﾞ]+","[ｸﾞﾘｯﾀｰｽﾃｰｼﾞ]+","[ｻﾌｧﾘｱﾄﾞﾍﾞﾝﾁｬｰ]+","[ﾛｺｶﾞｰﾙ]+","シンデレラガールズ"),
            arrayOf("\"アニメ「アイドルマスターシンデレラガールズ」21話にて結成された神崎蘭子と白坂小梅のユニット\"\"Rosenburg Alptraum\"\"。日本語に訳すと次のうちどれ？\"","茨の城の悪夢","茨の城の天使","茨の城の月蝕","茨の城の日蝕","シンデレラガールズ"),
            arrayOf("アニメ「アイドルマスターシンデレラガールズ」22話、オータムフェスにて白坂小梅、木村夏樹、安部菜々を加えた新体制で披露された「GOIN'!!!」でアイドル達が着ている衣装は次のうちどれ？","マイファーストスター","クリスタルナイトパーティ","スターリースカイ･ブライト","アクロス･ザ･スターズ","シンデレラガールズ")
            )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random_heard)

        countLabel = findViewById(R.id.countLabel)
        questionLabel = findViewById(R.id.questionLabel)
        answerBtn1 = findViewById(R.id.answerBtn1)
        answerBtn2 = findViewById(R.id.answerBtn2)
        answerBtn3 = findViewById(R.id.answerBtn3)
        answerBtn4 = findViewById(R.id.answerBtn4)

        //クイズデータquizDataからクイズ出題用のquizArrayを作成する
        for (i in quizData.indices) {

            //新しいArrayListを準備
            val tmpArray = ArrayList<String>()

            //クイズデータを追加
            tmpArray.add(quizData[i][0]) //問題文
            tmpArray.add(quizData[i][1]) //正解
            tmpArray.add(quizData[i][2]) //選択肢1
            tmpArray.add(quizData[i][3]) //選択肢2
            tmpArray.add(quizData[i][4]) //選択肢3
            tmpArray.add(quizData[i][5]) //genre

            //tmpArrayをquizArrayに追加
            quizArray.add(tmpArray)
        }
        showNextQuiz()
    }

    fun showNextQuiz() {
        //クイズカウントラベルを更新
        countLabel!!.text = "Q$quizCount"

        //ランダムな数字を取得する
        val random = Random()
        val randomNum = random.nextInt(quizArray.size)

        // randomNumを使って、quizArrayからクイズをひとつ取り出す
        val quiz = quizArray[randomNum]

        //問題文を表示
        genre = quiz[5]
        questionLabel!!.text = quiz[0] + " ジャンル:" + genre

        //正解をrightAnswerにセット
        rightAnswer = quiz[1]

        //クイズ配列から問題文を削除
        quiz.removeAt(0)
        quiz.removeAt(4)

        //正解と選択肢3つシャッフル
        Collections.shuffle(quiz)

        //回答ボタンに正解と選択肢3つを表示
        answerBtn1!!.text = quiz[0]
        answerBtn2!!.text = quiz[1]
        answerBtn3!!.text = quiz[2]
        answerBtn4!!.text = quiz[3]

        //このクイズをquizArrayから削除
        quizArray.removeAt(randomNum)
    }

    fun checkAnswer(view: View) {
        //どの回答ボタンが押されたか
        val answerBtn = findViewById<Button>(view.id)
        val btnText = answerBtn.text.toString()

        val alertTitle: String
        if (btnText == rightAnswer) {
            alertTitle = "正解!!"
            rightAnswerCount++
        }else {
            alertTitle = "不正解..."
        }

        //ダイアログを作成
        val builder = AlertDialog.Builder(this)
        builder.setTitle(alertTitle)
        builder.setMessage("答え:" + rightAnswer!!)
        builder.setPositiveButton("OK") { dialogInterface, i ->
            if (quizCount == QUIZ_COUNT) {
                //結果画面へ移動
                val intent = Intent(applicationContext, ResultActivity::class.java)
                intent.putExtra("RIGHT_ANSWER_COUNT",rightAnswerCount)
                startActivity(intent)
            } else {
                quizCount++
                showNextQuiz()
            }
        }
        builder.setCancelable(false)
        builder.show()
    }
    companion object {
        private val QUIZ_COUNT = 10
    }
}
