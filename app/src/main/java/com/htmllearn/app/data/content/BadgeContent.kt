package com.htmllearn.app.data.content

import com.htmllearn.app.data.model.BadgeInfo

val allBadges = listOf(
    BadgeInfo("first_lesson","প্রথম পদক্ষেপ","প্রথম lesson সম্পন্ন করেছো","ic_badge_star",lessonsRequired=1),
    BadgeInfo("beginner","নবীন শিক্ষার্থী","৫টি lesson সম্পন্ন","ic_badge_book",lessonsRequired=5),
    BadgeInfo("explorer","অন্বেষণকারী","১০টি lesson সম্পন্ন","ic_badge_explore",lessonsRequired=10),
    BadgeInfo("learner","শিক্ষার্থী","২০টি lesson সম্পন্ন","ic_badge_learn",lessonsRequired=20),
    BadgeInfo("scholar","পণ্ডিত","৩০টি lesson সম্পন্ন","ic_badge_scholar",lessonsRequired=30),
    BadgeInfo("master","দক্ষ","৫০টি lesson সম্পন্ন","ic_badge_master",lessonsRequired=50),
    BadgeInfo("xp_100","XP ১০০","১০০ XP অর্জন","ic_badge_xp",xpRequired=100),
    BadgeInfo("xp_500","XP ৫০০","৫০০ XP অর্জন","ic_badge_fire",xpRequired=500),
    BadgeInfo("xp_1000","XP ১০০০","১০০০ XP অর্জন","ic_badge_crown",xpRequired=1000),
    BadgeInfo("xp_5000","XP পাঁচ হাজার","৫০০০ XP অর্জন","ic_badge_diamond",xpRequired=5000),
    BadgeInfo("streak_3","৩ দিনের ধারা","৩ দিন ধারাবাহিকভাবে পড়েছো","ic_badge_flame",streakRequired=3),
    BadgeInfo("streak_7","সপ্তাহের যোদ্ধা","৭ দিন ধারাবাহিক","ic_badge_week",streakRequired=7),
    BadgeInfo("streak_30","মাসের সেরা","৩০ দিন ধারাবাহিক","ic_badge_month",streakRequired=30),
    BadgeInfo("quiz_ace","Quiz তুরুপ","১০টি quiz তে ১০০% পেয়েছো","ic_badge_quiz"),
    BadgeInfo("html_basics","HTML মূল শেষ","Chapter 1 সম্পন্ন","ic_badge_html"),
    BadgeInfo("semantic_hero","Semantic নায়ক","Semantic chapter সম্পন্ন","ic_badge_semantic"),
    BadgeInfo("project_1","প্রথম প্রকল্প","প্রথম project সম্পন্ন","ic_badge_project"),
    BadgeInfo("night_owl","রাতের পেঁচা","রাত ১১টার পর পড়েছো","ic_badge_night"),
    BadgeInfo("early_bird","ভোরের পাখি","সকাল ৬টার আগে পড়েছো","ic_badge_morning"),
    BadgeInfo("speedster","দ্রুতগামী","৫ মিনিটের কম সময়ে lesson শেষ","ic_badge_speed")
)
