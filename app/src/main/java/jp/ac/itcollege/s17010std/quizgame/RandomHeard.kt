package jp.ac.itcollege.s17010std.quizgame

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.util.*

class RandomHeard : AppCompatActivity() {

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
            // arrayOf("H問題文","正解","選択肢1","選択肢2","選択肢3")
            //s17012 quiz data
            arrayOf("TWICEは結成何年目か？(2018/12/01現在)","3年","13年","28年","1年","TWICE"),
            arrayOf("TWICEのメンバーはどれか","湊崎紗奈","ジヒョン","ジョンヒョン","タヒョン","TWICE"),
            arrayOf("TWICEの最年長はだれか？","ナヨン","ミナ","モモ","チェヨン","TWICE"),
            arrayOf("TTの歌いだしは誰か","ナヨン","ミナ","モモ","ジヒョ","TWICE"),
            arrayOf("KNOCK KNOCKで餅ゴリが持ってるものは？","キーボード","枕","ノート","メンバー表","TWICE"),
            arrayOf("日本シングル曲Candy popの監督はだれか？","京極尚彦","宮崎駿","手塚治虫","赤根和樹","TWICE"),
            arrayOf("TWICE JAPAN 1st FULL ALBUM『BDZ』のリパッケージ曲はどれか？","stay by my side","Wake me up","signal","dance the night away","TWICE"),
            arrayOf("TWICE 2017年紅白歌合戦で歌った曲は？","TT　japanese ver","TT","Candy pop","one more time","TWICE"),
            arrayOf("ハートシェイカーのMVでダンスミスを使われてしまったのは誰","ツウィ","サナ","ジヒョ","ナヨン","TWICE"),
            arrayOf("ケイダイ","ツウィ","サナ","モモ","ジョンヨン","TWICE"),
            //s16019 quiz data
            arrayOf("黒曜石と同等の爆破耐性を持つブロックは","金床","エンドストーン","鉄ブロック","ダイヤブロック","まいんくらふと"),
            arrayOf("山岳バイオームでは高さいくつから雪が降りますか","約95","約90","約100","降らない","まいんくらふと"),
            arrayOf("レッドストーンの動作(更新)は1秒間に何回発生しますか","10回","20回","30回","60回","まいんくらふと"),
            arrayOf("防具にダメージ軽減IVのエンチャントを付ける場合、何箇所で最大の効果が得られるか","4","1","2","3","まいんくらふと"),
            arrayOf("防具に爆発耐性IVのエンチャントを付ける場合、何箇所で最大の効果が得られるか","3","1","2","4","まいんくらふと"),
            arrayOf("虫特攻のエンチャント効果が発揮されるモンスターは","シルバーフィッシュ","ブレイズ","ファントム","ヴェックス","まいんくらふと"),
            arrayOf("司書との交易で「修繕」のエンチャント本を入手するのに必要なエメラルドの最小数はいくら","5","1","3","7","まいんくらふと"),
            arrayOf("進捗「真面目な献身」は何を行うと達成するか","ダイヤのクワを壊れるまで使い切る","36種類のバイオームを訪れる","村人ゾンビを治療する","ガストをオーバーワールドで殺す。","まいんくらふと"),
            arrayOf("クリエイティブモードで取り出すことが出来ないアイテムはどれか","花火","スポーンエッグ","エンドポータルフレーム","岩盤","まいんくらふと"),
            arrayOf("ネザーウォートを使用しないで醸造できるポーションは","弱化","毒","タートルマスター","低速落下","まいんくらふと"),
            //s17001 quiz data
            arrayOf("たいようを見上げると？","まぶしい","さむい","むかつく","悟る","闇鍋問題"),
            arrayOf("ルーマニアのファシスト派閥の名前は？","ルーマニア鉄衛団","選択肢1","選択肢2","選択肢3","闇鍋問題"),
            arrayOf("リモートでサーバーにログインする時に使うものは？","秘密鍵","公開鍵","免許書","マイナンバー","闇鍋問題"),
            arrayOf("2018年の恵方巻きの方角は？","南南東","南南西","東西南北","国士無双","闇鍋問題"),
            arrayOf("第二次大戦前イギリスに置ける兵器で、9回の実験全てで失敗を納めた奇跡の紅茶キメまくり兵器は？","パンジャンドラム","ツァーリタンク","ハボクック","コウモリ爆弾","闇鍋問題"),
            arrayOf("\"([][\"\"\"\"]+\"\"\"\")[-~[]- -~[]]+([][\"\"\"\"]+\"\"\"\")[-~[]]+(({})+[])[5]+(({})+[])[-~[]]\"","unko","hoge","TRUE","FALSE","闇鍋問題"),
            arrayOf("NK-POPの공격전이다：コンギョッチョニダ日本語訳すると？","攻城戦だ","南を撃ち落とせ","攻めろ","天下無敵","闇鍋問題"),
            arrayOf("ポケモンで急所の出る確率は？","1/24","1/8","1/32","1/16","闇鍋問題"),
            arrayOf("ドラクエモンスターズシリーズで、スライムを4体配合してできるモンスターは？","スライムキング","スライムベス","バブルスライム","つよぽん","闇鍋問題"),
            arrayOf("\"[[‘’,’’,’’,’’,’\uD83D\uDE1C’],[’’,’\uD83D\uDE03’,’\uD83D\uDE02’,’\uD83D\uDE43’,’\uD83D\uDE1C’],[‘’,’\uD83E\uDD14’,’\uD83D\uDE03’,’\uD83D\uDE02’,’\uD83D\uDE43’],[‘\uD83E\uDD14’,’\uD83D\uDE03’,’\uD83D\uDE02’,’\uD83D\uDE43’,’\uD83D\uDE1C’],[‘\uD83E\uDD14’,’\uD83D\uDE03’,’\uD83D\uDE02’,’\uD83D\uDE43’,’\uD83D\uDE1C’]]\"","5","4","6","7","闇鍋問題"),
            //s17002 quiz data
            arrayOf("一度も不参加せずに最初からオリンピックに参加している国はどこでしょう？","ギリシャ","中国","スイス","アメリカ","スポーツ"),
            arrayOf("東京オリンピックが開催されたのは1964年でしたがその前に開催されるはずでした、1964年に変更された理由は何でしょう？","戦争が勃発してそれどころではなかった","日本は経済的に困窮していて、資金がなかったから。","外国からの人々を受け入れる施設の状態が悪かったから","外国人差別がひどかったから","スポーツ"),
            arrayOf("以前オリンピックでの正式種目は何でしょう？","綱引き","騎馬戦","組体操","二人三脚","スポーツ"),
            arrayOf("ボールを前に投げると反則になるスポーツはどれでしょう？","ラグビー","サッカー","アメフト","水球","スポーツ"),
            arrayOf("0世紀で最初にオリンピックが開催された都市はどこでしょう？","セントルイス","アトランタ","アテネ","シドニー","スポーツ"),
            arrayOf("ロシアワールドカップで大会MVPになった選手は誰でしょう？","モドリッチ","メッシー","ロナウド","ムバッペ","スポーツ"),
            arrayOf("Tリーグは何のスポーツリーグでしょう？","卓球","バスケ","バトミントン","テニス","スポーツ"),
            arrayOf("ボクシング、フロイドメイウェザー選手は何階級制覇したでしょう？","５階級","3階級","4階級","7階級","スポーツ"),
            arrayOf("バスケのレブロンジェイムズ選手が生涯契約したブランドはどこでしょう？","ナイキ","アンダーアーマ","アディダス","ニューバランス","スポーツ"),
            arrayOf("WBSSとは何の大会でしょう？","ボクシング","バスケ","フェンシング","柔道","スポーツ"),
            //s17003 quiz data
            arrayOf("紅魔郷にて、一面ボスルーミアのセリフを答えなさい。","「聖者は十字架に磔られました」っていっているように見える？","巫女は食べてもいい人類だって言い伝えが．．．","巫女は食べてもいい人類だって言い伝えが．．．","あなたが、コンティニュー出来ないのさ！","東方project"),
            arrayOf("物部布都は何でしょう？","尸解仙（しかいせん）","吸血鬼","キョンシー","魔法使い","東方project"),
            arrayOf("( ﾟ∀ﾟ)o彡゜ ← どういう意味？","八意永琳","上白沢慧音","雲山","聖白蓮","東方project"),
            arrayOf("東方projectの二次創作で、東方M-1グランプリというものがあります。その作品内にて、紅魔館は何回燃えたのでしょうか？","二回","一回","一回","そもそも燃えたことがない","東方project"),
            arrayOf("八雲藍の好物は何？","油揚げ","唐揚げ","かき揚げ","またたび","東方project"),
            arrayOf("東方風神録にて、二面ステージの道中BGMは何でしょうか？","厄神様の通り道 ～ Dark Road","運命のダークサイド","フォールオブフォール ～ 秋めく滝","神々が恋した幻想郷","東方project"),
            arrayOf("東方地霊殿に登場する、六面ボス霊烏路空が取り込んだのは？","ヤタガラス","カグツチ","タケミカヅチ","アマテラス","東方project"),
            arrayOf("魔理沙の使う道具にミニ八卦炉がありますが、これの製作者は誰？","森近霖之助","八雲紫","アリス・マーガトロイド","そもそも本人の自作","東方project"),
            arrayOf("東方永夜抄にて、博麗霊夢のラストワードは何？","夢想天生","夢想封印","二重大結界","陰陽鬼神玉","東方project"),
            arrayOf("東方projectの舞台である幻想郷のモデルである、と言われている土地は何処にある？","長野","青森","福岡","茨城","東方project"),
            //s17004 quiz data
            arrayOf("レベル32で進化するポケモンはどれ？","フシギバナ","リザードン","カメックス","ライチュウ","ポケモン"),
            arrayOf("コイキングがギャラドスに進化するレベルは？","20","25","30","35","ポケモン"),
            arrayOf("クサイハナがラフレシアに進化するレベルは？","リーフの石を使う","太陽の石を使う","20","30","ポケモン"),
            arrayOf("次のうちレベル３４で最終進化するのはどれ？","全員","モクロー","アシマリ","ニャビー","ポケモン"),
            arrayOf("次のうち進化するのにアイテムが必要ないポケモンはどれ？","ゴースト","ヤドン","プリン","ブーバー","ポケモン"),
            arrayOf("最終進化が複数あるポケモンはどれ？","ラルトス","ポリゴン","ヤミカラス","ヒマナッツ","ポケモン"),
            arrayOf("リージョンフォームがないポケモンはどれ？","カラカラ","ニャース","ライチュウ","ディグダ","ポケモン"),
            arrayOf("フォルムチェンジがあるポケモンはどれ？","ゲノセクト","ルカリオ","ダークライ","パルキア","ポケモン"),
            arrayOf("イーブイ関連でない属性はどれ？","むし","フェアリー","こおり","エスパー","ポケモン"),
            arrayOf("伝説ポケモンではないのはどれ？","セレビィ","カイオーガ","レックウザ","グラードン","ポケモン"),
            //s17005 quiz data
            arrayOf("グランブルーファンタジーの主人公２名の名前はその題名から取っている。主人公２名の名前は","グランとジータ","ランブルとタジー","グランとジーファ","ラブルとファージ","サイゲアプリ"),
            arrayOf("アイドルマスターシンデレラガールズの中心ユニットである３人→「島村卯月・渋谷凛・本田未央」によるユニットの名前は","ニュージェネレーション","ニューウェーブ","シンデレラプロジェクト","ワンステップス","サイゲアプリ"),
            arrayOf("シャドウバースはカードゲームでありながらそのゲームバランスから様々なものに例えられる。この中で呼ばれたことがない例えは","○×ゲーム","めんこ","コイントス","ソリティア","サイゲアプリ"),
            arrayOf("プリンセスコネクトＲｅｄｉｖｅではキャラ達が色々な集まりを組んでいる。その集まりの名前は","ギルド","グループ","パーティー","ファミリー","サイゲアプリ"),
            arrayOf("グランブルーファンタジーでは１０種類の武器がありそれぞれの最強の使い手達で組まれたチームがある。そのチームの名前は","十天衆","十神合","十強人","十連党","サイゲアプリ"),
            arrayOf("シャドウバースのカードにはそれぞれレアリティが設定されている。最高レアリティの名前は","レジェンド","エピック","ＳＳＲ","プレミアム","サイゲアプリ"),
            arrayOf("アイドルマスターシンデレラガールズには様々な年代のアイドルが数多くいる。正しい最年少と最年長の組み合わせはどれか","９歳と３１歳","１０歳と３１歳","９歳と２９歳","１０歳と２９歳","サイゲアプリ"),
            arrayOf("アイドルマスターシンデレラガールズでは所謂「石」を使ってガシャを回したり色々なアイテムを購入したりすることができる。その「石」の名前は","スタージュエル","シンデレラストーン","宝晶石","ムーンクリスタル","サイゲアプリ"),
            arrayOf("プリンセスコネクトＲｅｄｉｖｅでは育てたキャラを戦わせる所謂ＰｖＰコンテンツがある。そのコンテンツの名称は","アリーナ","トーナメント","リーグ","古戦場","サイゲアプリ"),
            arrayOf("グランブルーファンタジーは色々な作品とコラボをしているが、この中で２０１８年１１月現在コラボしていない作品はどれか","プリンセスコネクト","プリキュア","ストリートファイター","アイドルマスターシンデレラガールズ","サイゲアプリ"),
            //s17007 quiz data
            arrayOf("次のうち、「機動戦士ガンダム ガンダムVS.ガンダムNEXT PLUS」において使用できない機体はどれか","ダブルオークアンタ","クシャトリヤ","ガンダムヴァーチェ","アカツキ","ロボ"),
            arrayOf("次のうち、宇宙世紀でない作品からは初のMGキット化を遂げた機体はどれか","GNX-603T","ZGMF-1017","RGE-G2100","OZ-06MS","ロボ"),
            arrayOf("「機動戦士ガンダム MSV-R ジョニー・ライデンの帰還」において主人公の上司であるフーバーが搭乗した機体は次のうちどれか","RGM-79KC","RGM-79KC","RGM-79SC","RGM-79V","ロボ"),
            arrayOf("次のうち「フレームアームズ」シリーズにおいて月面側が開発、運用したと設定されている機体はどれか","NSG-X1","YSX-24RD/NE","EXF-10/52","LX-R01J","ロボ"),
            arrayOf("次のうち「機動戦士ガンダム ガンダムVS.ガンダムNEXT PLUS」と「機動戦士ガンダム EXTREME VS. MAXI BOOST ON」において機体コストに違いがあるものはどれか","フリーダムガンダム","ユニコーンガンダム","ガンダムエクシア","ウイングガンダムゼロ","ロボ"),
            arrayOf("次の「マクロスΔ」に登場する機体のうち、バンダイからプラモデルが販売されなかったものはどれか","VF-31A","VF-31F","Sv-262Hs","SV-262Ba","ロボ"),
            arrayOf("次のうち「機動戦士ガンダム ギレン暗殺計画」に登場しなかったMSはどれか","MS-06J","MS-06FZ","MS-07B-3","MS-17","ロボ"),
            arrayOf("次のうち、ランナーの枚数が最も多いキットはどれか","HGCE ストライクフリーダムガンダム","HGUC ユニコーンガンダム(デストロイモード)","HG ガンダムバルバトスルプスレクス","MG  ボール(Ver.Ka)","ロボ"),
            arrayOf("次のうち、定価が最も高額なキットはどれか","RG フルアーマーユニコーンガンダム","RG サザビー","MG ジャスティスガンダム","MG デスティニーガンダム","ロボ"),
            arrayOf("次のうち、戦場の絆(REV2.x)時点において機体カテゴリが違うMSがはどれか","ジム(指揮官機)","ジム","ジム(バズーカラック仕様)","ジム(WD隊)","ロボ"),
            //s17009 quiz data
            arrayOf("YAMAHA YZF-R1搭載されてるエンジン特徴に合っているものはどれか","クロスプレーンエンジン","V4エンジン","水平対向４気筒エンジン","並列二気筒エンジン","タスゴン、おバイク"),
            arrayOf("MT-09のエンジンはどれか","3気筒エンジン","2気筒エンジン","単気筒エンジン","4気筒エンジン","タスゴン、おバイク"),
            arrayOf("Ducatiのメインカラーはどれか","赤","緑","青","白","タスゴン、おバイク"),
            arrayOf("スロットル・バイ・ワイヤシステムを採用しているバイクはどれか","CBR250RR","CB400SF","CB250R","CB400SB","タスゴン、おバイク"),
            arrayOf("CBR1000RR SPに採用されているサスペンションのメーカーはどれか","OHLINS","YSS","SHOWA","QUANTUM","タスゴン、おバイク"),
            arrayOf("ヘルメット PISTA GP Rを製造しているメーカーはどれか","AGV","SHOEI","Arai","SIMPSON","タスゴン、おバイク"),
            arrayOf("ライディングウェアメーカー、Alpinestarsの本社はどこか","イタリア　アゾロ","フランス　パリ","ドイツ　アルトナ","スペイン　アルバセーテ","タスゴン、おバイク"),
            arrayOf("YAMAHA発動機が開発したAI搭載型二輪の名前は","MOTOROiD","MOTOROID","MotoRoid","MotoROID","タスゴン、おバイク"),
            arrayOf("Motogp Ducatiチームに所属している選手はどれか","ホルヘ・ロレンソ","マルク・マルケス","ダニ・ペドロサ","バレンティーノ・ロッシ","タスゴン、おバイク"),
            arrayOf("DUNLOP製の二輪用タイヤはどれか","a14","BATTLAX","SPORTEC M7 RR","選択肢3","タスゴン、おバイク"),
            //s17010 quiz data
            arrayOf("森高千里の[私がオバさんになっても]より、夏休みに二人して行った場所は？","サイパン","グアム","ハワイ","沖縄","音楽"),
            arrayOf("平原綾香の[Jupiter]原曲の作曲家は？","ホルスト","トルストイ","ベートーヴェン","ドボルザーク","音楽"),
            arrayOf("中島みゆきの[地上の星]より正しくない組み合わせは？","草原のシリウス","崖の上のジュピター","砂の中の銀河","街角のヴィーナス","音楽"),
            arrayOf("次の中で最も長いタイトルの曲をリリースしたのはどれ？","BEGIN","AKB48","B'Z","ももいろクローバーZ","音楽"),
            arrayOf("チェッカーズの[ジュリアに傷心]”傷心”のよみは？","ハートブレイク","きずごころ","ブレイクハート","スカーハート","音楽"),
            arrayOf("植村花菜の[トイレの神様]の曲の長さは？","9分52秒","9分48秒","10分07秒","8分33秒","音楽"),
            arrayOf("Whiteberryの[夏祭り]のカバー元のアーティストは？","JITTERIN'JINN","そもそも原曲","長渕剛","TUBE","音楽"),
            arrayOf("曲名当て：♬キャンドルライトの中の二人を","乾杯","くしゃみじゃなくてよかったよ","X'mas in the Bule","クリスマスソング","音楽"),
            arrayOf("曲名当て：♬不愉快に冷たい壁とか","月光","混濁","White Breath","ray","音楽"),
            arrayOf("次の内「桜」を歌っているのは誰？","徳永英明","松田聖子","中島みゆき","秦基博","音楽"),
            //s17011 quiz data
            arrayOf("\"アニメ「アイドルマスターシンデレラガールズ」21話にて結成された神崎蘭子と白坂小梅のユニット\"\"Rosenburg Alptraum\"\"。日本語に訳すと次のうちどれ？\"","茨の城の悪夢","茨の城の天使","茨の城の月蝕","茨の城の日蝕","シンデレラガールズ"),
            arrayOf("アニメ「アイドルマスターシンデレラガールズ」第6話にて有浦柑奈、白坂小梅、二宮飛鳥、早坂美玲、松永涼らがガールズバンドを組んで清涼飲料水のCM撮影をしているシーンがあります。この清涼飲料水の商品名は次のうちどれ？","Panta Grape","Sqlite","Mr Copper","四谷サイダー","シンデレラガールズ")
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
            tmpArray.add(quizData[i][5]) //ジャンル


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
