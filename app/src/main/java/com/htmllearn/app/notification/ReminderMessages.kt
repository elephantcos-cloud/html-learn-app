package com.htmllearn.app.notification

object ReminderMessages {
    val morningMessages = listOf(
        Pair("সুপ্রভাত! HTML শেখার সময় হয়েছে ☀️", "আজকে একটু সময় নিয়ে HTML শেখো। প্রতিদিন একটু একটু করেই দক্ষ হওয়া যায়!"),
        Pair("ভোরের শিক্ষার্থী!", "সকালবেলা মাথা তাজা থাকে — এখনই HTML এর নতুন lesson দেখো।"),
        Pair("শুভ সকাল! আজকের চ্যালেঞ্জ", "আজ কি নতুন কিছু শিখবে? HTML তোমার জন্য অপেক্ষা করছে!"),
        Pair("ঘুম ভালো হয়েছে?", "তাহলে এবার মস্তিষ্ককে কাজে লাগাও — একটা HTML lesson শেষ করো আজ।")
    )

    val afternoonMessages = listOf(
        Pair("দুপুরের বিরতিতে HTML!", "খাওয়ার পর একটু rest নিয়ে HTML শেখায় মন দাও।"),
        Pair("আজকের লক্ষ্য পূরণ হয়নি!", "দিন শেষ হওয়ার আগে অন্তত একটি lesson শেষ করো।"),
        Pair("Streak বাঁচাও!", "তোমার learning streak বাঁচাতে এখনই একটি lesson দেখো।"),
        Pair("এক কাপ চা আর HTML!", "চায়ের সাথে আজকের HTML lesson — perfect combination!")
    )

    val eveningMessages = listOf(
        Pair("সন্ধ্যার শেখার সময়!", "দিনের শেষে মাথা ঠান্ডা রেখে HTML শেখো।"),
        Pair("আজকের goal কি পূরণ হয়েছে?", "না হলে এখনও সময় আছে! একটি lesson দিয়ে শুরু করো।"),
        Pair("রাত হওয়ার আগে শেখো!", "এখনই HTML শিখলে ঘুমের মধ্যে brain সেটা process করবে।"),
        Pair("Daily streak মিস হয়ে যাচ্ছে!", "আজকের streak টিকিয়ে রাখতে এখনই app খোলো।")
    )

    val lateNightMessages = listOf(
        Pair("রাতের পেঁচা HTML শেখে!", "রাত হলেও শেখার সেরা সময়। একটি lesson শেষ করো।"),
        Pair("আজকের শেষ সুযোগ!", "মধ্যরাত হওয়ার আগে আজকের lesson সম্পন্ন করো।"),
        Pair("ঘুমানোর আগে HTML!", "মাত্র ১০ মিনিট — একটি lesson দেখো, তারপর শান্তিতে ঘুমাও।")
    )

    val motivationalMessages = listOf(
        Pair("তুমি পারবে!", "প্রতিটি expert একসময় beginner ছিল। শেখা চালিয়ে যাও!"),
        Pair("${"\uD83D\uDCAA"} শক্তিশালী হও!", "প্রতিদিন একটু HTML শিখলে এক মাসে তুমি অনেক এগিয়ে যাবে।"),
        Pair("লক্ষ্য স্থির রাখো!", "Web developer হওয়ার স্বপ্ন পূরণের পথে HTML প্রথম পদক্ষেপ।"),
        Pair("Quiz তে ভালো করো!", "আজকের lesson এর quiz চেষ্টা করো — জ্ঞান যাচাই করো।"),
        Pair("তোমার XP বাড়ছে!", "আরো lesson শেষ করো, XP জমাও, নতুন badge পাও!"),
        Pair("Streak মিস করো না!", "ধারাবাহিকতাই সাফল্যের চাবিকাঠি। আজকের lesson দেখো।"),
        Pair("নতুন chapter অপেক্ষা করছে!", "আজকের lesson শেষ করলে নতুন chapter unlock হবে।"),
        Pair("HTML দিয়ে website বানাও!", "শেখা শেষ হলে তুমি নিজেই website বানাতে পারবে। শিখতে থাকো!")
    )

    fun getRandom(hour: Int): Pair<String, String> {
        val list = when {
            hour in 5..11 -> morningMessages
            hour in 12..16 -> afternoonMessages
            hour in 17..20 -> eveningMessages
            hour in 21..23 -> lateNightMessages
            else -> motivationalMessages
        }
        return list.random()
    }
}
