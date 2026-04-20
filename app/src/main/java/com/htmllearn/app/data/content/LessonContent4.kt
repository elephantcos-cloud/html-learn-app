package com.htmllearn.app.data.content

import com.htmllearn.app.data.model.*

val chapter6 = Chapter(
    id = "ch6", title = "Meta ও SEO", description = "Search Engine Optimization এর মূল কথা",
    color = 0xFF9C27B0,
    lessons = listOf(
        Lesson(
            id = "ch6_l1", chapterId = "ch6", title = "Meta Tags", xp = 35, duration = "১০ মিনিট",
            explanation = """
Meta tags <head> এর ভেতরে থাকে এবং page সম্পর্কে তথ্য দেয়।

📌 Charset:
<meta charset="UTF-8"> — সব ভাষা সাপোর্ট

📌 Viewport:
<meta name="viewport" content="width=device-width, initial-scale=1.0">
Mobile friendly করে।

📌 Description:
<meta name="description" content="Page এর সারাংশ">
Google search result এ দেখায়।

📌 Keywords (পুরানো):
<meta name="keywords" content="html, css, web">
এখন Google এটা বেশি গুরুত্ব দেয় না।

📌 Author:
<meta name="author" content="তোমার নাম">

📌 Open Graph (Social Media):
Facebook, Twitter share এর জন্য।
og:title, og:description, og:image

📌 Robots:
<meta name="robots" content="index,follow">
Search engine কে crawl করতে বলে।
            """.trimIndent(),
            codeExample = """<!DOCTYPE html>
<html lang="bn">
<head>
  <!-- Charset -->
  <meta charset="UTF-8">
  
  <!-- Mobile Friendly -->
  <meta name="viewport" 
    content="width=device-width,
             initial-scale=1.0">
  
  <!-- SEO -->
  <meta name="description" 
    content="HTML শেখার সেরা বাংলা 
             গাইড। Beginner থেকে 
             Advanced।">
  
  <meta name="keywords" 
    content="html, বাংলা, শেখা">
  
  <meta name="author" 
    content="তোমার নাম">
  
  <!-- Social Media -->
  <meta property="og:title" 
    content="HTML শেখো বাংলায়">
  
  <meta property="og:description" 
    content="সহজ বাংলায় HTML শেখো">
  
  <meta property="og:image" 
    content="https://example.com/img.jpg">
  
  <!-- Robots -->
  <meta name="robots" 
    content="index, follow">
  
  <title>HTML শেখো বাংলায় | 
         সেরা গাইড</title>
</head>
</html>""",
            outputHtml = """<div style="background:#1e1e1e;padding:16px;border-radius:8px;color:white"><p style="color:#9C27B0;font-weight:bold;margin:0 0 12px">Meta Tags Preview</p><div style="background:#2a2a2a;padding:12px;border-radius:8px;border-left:3px solid #6C63FF"><p style="color:#999;font-size:11px;margin:0">htmllearn.example.com</p><p style="color:#6C63FF;margin:4px 0;font-weight:bold">HTML শেখো বাংলায় | সেরা গাইড</p><p style="color:#ccc;font-size:13px;margin:0">HTML শেখার সেরা বাংলা গাইড। Beginner থেকে Advanced।</p></div><p style="color:#666;font-size:11px;margin-top:8px">↑ Google Search Result এ এভাবে দেখাবে</p></div>""",
            quizzes = listOf(
                Quiz("viewport meta tag কেন দরকার?",listOf("Page speed বাড়ায়","Mobile device এ সঠিকভাবে দেখায়","Image load করে","Font বদলায়"),1,"viewport meta tag mobile device এ page সঠিকভাবে scale করে।"),
                Quiz("og:image meta কোথায় ব্যবহার হয়?",listOf("Google search এ","Social media share এ","Browser tab এ","Page footer এ"),1,"Open Graph (og:) tags social media share এর সময় কাজ করে।")
            )
        ),
        Lesson(
            id = "ch6_l2", chapterId = "ch6", title = "SEO Best Practices", xp = 40, duration = "১২ মিনিট",
            explanation = """
SEO (Search Engine Optimization) — Google এ page কে উপরে আনার কৌশল।

📌 HTML SEO Tips:

1️⃣ Proper Heading Structure:
একটি h1, তারপর h2, h3 ক্রমে।

2️⃣ Meaningful URLs:
/html-tutorial/heading-tags (ভালো)
/page?id=123 (খারাপ)

3️⃣ Alt Text:
সব image এ alt দেওয়া।

4️⃣ Internal Links:
নিজের site এর অন্য page এর link।

5️⃣ Page Speed:
Image optimize, lazy loading।

6️⃣ Structured Data:
JSON-LD দিয়ে Google কে data বোঝানো।

7️⃣ Canonical Tag:
Duplicate content এড়াতে।
<link rel="canonical" href="...">

8️⃣ Sitemap:
XML sitemap তৈরি ও submit।

📌 Core Web Vitals:
LCP — সবচেয়ে বড় element কত দ্রুত load
FID — প্রথম interaction delay
CLS — layout shift পরিমাণ
            """.trimIndent(),
            codeExample = """<!DOCTYPE html>
<html lang="bn">
<head>
  <meta charset="UTF-8">
  <title>HTML Tutorial | Heading Tags</title>
  <meta name="description" 
    content="HTML heading tags h1-h6 
    সম্পর্কে বিস্তারিত জানুন।">
  
  <!-- Canonical -->
  <link rel="canonical" 
    href="https://example.com/html/heading">
  
  <!-- Structured Data -->
  <script type="application/ld+json">
  {
    "@context": "https://schema.org",
    "@type": "Article",
    "headline": "HTML Heading Tags",
    "author": {
      "@type": "Person",
      "name": "তোমার নাম"
    },
    "datePublished": "2026-04-20"
  }
  </script>
</head>
<body>
  <h1>HTML Heading Tags</h1>
  <h2>h1 Tag কী?</h2>
  <p>h1 সবচেয়ে গুরুত্বপূর্ণ...</p>
  
  <!-- Lazy Loading Image -->
  <img src="diagram.jpg" 
       alt="Heading hierarchy diagram"
       loading="lazy">
</body>
</html>""",
            outputHtml = """<div style="background:#1e1e1e;padding:16px;border-radius:8px;color:white"><div style="display:flex;gap:8px;margin-bottom:12px"><div style="flex:1;background:#2a2a2a;padding:10px;border-radius:6px;text-align:center"><p style="color:#4CAF50;font-size:20px;font-weight:bold;margin:0">95</p><p style="color:#999;font-size:11px;margin:4px 0 0">SEO Score</p></div><div style="flex:1;background:#2a2a2a;padding:10px;border-radius:6px;text-align:center"><p style="color:#6C63FF;font-size:20px;font-weight:bold;margin:0">✓</p><p style="color:#999;font-size:11px;margin:4px 0 0">Mobile Friendly</p></div><div style="flex:1;background:#2a2a2a;padding:10px;border-radius:6px;text-align:center"><p style="color:#00BCD4;font-size:20px;font-weight:bold;margin:0">A+</p><p style="color:#999;font-size:11px;margin:4px 0 0">Performance</p></div></div><p style="color:#666;font-size:12px">↑ ভালো SEO practice এ এই score পাওয়া সম্ভব</p></div>""",
            quizzes = listOf(
                Quiz("SEO এর পূর্ণরূপ কী?",listOf("Search Engine Optimization","Server Engine Operation","Site Engine Output","Search Engine Operation"),0,"SEO = Search Engine Optimization।"),
                Quiz("loading='lazy' attribute কী করে?",listOf("Image দ্রুত load করে","Image scroll করার সময় load হয়","Image hide করে","Image compress করে"),1,"loading='lazy' দিলে image viewport এ আসলে তখন load হয় — page speed বাড়ে।")
            )
        )
    )
)

val chapter7 = Chapter(
    id = "ch7", title = "Accessibility", description = "সবার জন্য ব্যবহারযোগ্য ওয়েব",
    color = 0xFF009688,
    lessons = listOf(
        Lesson(
            id = "ch7_l1", chapterId = "ch7", title = "Web Accessibility কী?", xp = 30, duration = "৮ মিনিট",
            explanation = """
Web Accessibility মানে ওয়েবসাইট এমনভাবে বানানো যাতে প্রতিবন্ধী মানুষরাও ব্যবহার করতে পারেন।

📌 কারা উপকৃত হন?
• দৃষ্টিহীন — screen reader ব্যবহার করেন
• বধির — closed captions দরকার
• মোটর সমস্যা — শুধু keyboard ব্যবহার করেন
• শেখার সমস্যা — সহজ ভাষা দরকার

📌 WCAG Guidelines:
Perceivable — সবাই বুঝতে পারবে
Operable — সবাই ব্যবহার করতে পারবে
Understandable — সহজবোধ্য
Robust — সব technology তে কাজ করবে

📌 ARIA Attributes:
role — element এর role বলে
aria-label — accessible নাম
aria-hidden — screen reader থেকে লুকানো
aria-expanded — dropdown open/close
tabindex — keyboard navigation ক্রম

📌 HTML এ Accessibility:
• সব image এ alt দাও
• Semantic HTML ব্যবহার করো
• Form এ label দাও
• Color only দিয়ে তথ্য দিও না
• যথেষ্ট color contrast রাখো
            """.trimIndent(),
            codeExample = """<!-- ভুল পদ্ধতি -->
<div onclick="submit()">
  জমা দিন
</div>

<!-- সঠিক পদ্ধতি -->
<button type="submit" 
        aria-label="ফর্ম জমা দিন">
  জমা দিন
</button>

<!-- Skip Navigation -->
<a href="#main" 
   class="skip-link">
  মূল content এ যান
</a>

<!-- Accessible Form -->
<form>
  <label for="search">
    খোঁজার শব্দ:
  </label>
  <input type="search" 
         id="search"
         aria-label="পণ্য খুঁজুন"
         placeholder="পণ্যের নাম...">
  
  <button type="submit"
          aria-label="খোঁজো">
    খোঁজো
  </button>
</form>

<!-- Accessible Image -->
<img src="chart.png" 
     alt="২০২৬ সালের 
          বিক্রির চার্ট: 
          জানুয়ারিতে সর্বোচ্চ">

<!-- Role attribute -->
<div role="alert">
  সফলভাবে সংরক্ষিত হয়েছে!
</div>""",
            outputHtml = """<div style="background:#1e1e1e;padding:16px;border-radius:8px;color:white"><div style="background:#2a2a2a;padding:12px;border-radius:6px;margin-bottom:12px"><label style="color:#009688;display:block;margin-bottom:4px">খোঁজার শব্দ:</label><div style="display:flex;gap:8px"><input type="text" placeholder="পণ্যের নাম..." style="flex:1;padding:8px;background:#1e1e1e;border:1px solid #009688;border-radius:6px;color:white"><button style="background:#009688;color:white;padding:8px 16px;border:none;border-radius:6px">খোঁজো</button></div></div><div style="background:#1a3a1a;border:1px solid #4CAF50;padding:12px;border-radius:6px" role="alert"><span style="color:#4CAF50">✓ </span><span>সফলভাবে সংরক্ষিত হয়েছে!</span></div></div>""",
            quizzes = listOf(
                Quiz("aria-label কী কাজ করে?",listOf("Element style করে","Screen reader এর জন্য accessible নাম দেয়","Element লুকায়","Link তৈরি করে"),1,"aria-label screen reader ব্যবহারকারীদের জন্য element এর accessible নাম দেয়।"),
                Quiz("Accessibility তে alt text কেন জরুরি?",listOf("Image সুন্দর করে","দৃষ্টিহীনরা screen reader দিয়ে জানতে পারেন","Image load দ্রুত হয়","SEO একমাত্র কারণ"),1,"alt text দৃষ্টিহীন ব্যবহারকারীদের জন্য image সম্পর্কে জানায়।")
            )
        )
    )
)

val chapter8 = Chapter(
    id = "ch8", title = "Advanced Forms", description = "Forms এর উন্নত ব্যবহার",
    color = 0xFFE91E63,
    lessons = listOf(
        Lesson(
            id = "ch8_l1", chapterId = "ch8", title = "Form Validation", xp = 45, duration = "১৫ মিনিট",
            explanation = """
HTML5 এ built-in form validation আছে।

📌 Required:
required — খালি রাখা যাবে না

📌 Length:
minlength="8" — কমপক্ষে ৮ character
maxlength="50" — সর্বোচ্চ ৫০ character

📌 Number Range:
min="0" max="100" — ০-১০০ এর মধ্যে
step="5" — ৫ ৫ করে বাড়বে

📌 Pattern:
Regular expression দিয়ে format check।
pattern="[0-9]{11}" — ১১ সংখ্যার number
pattern="[A-Za-z]+" — শুধু অক্ষর

📌 Type Validation:
type="email" — email format check
type="url" — URL format check
type="number" — শুধু সংখ্যা

📌 Custom Messages:
setCustomValidity() দিয়ে বাংলায় error।

📌 novalidate:
<form novalidate> — HTML validation বন্ধ
নিজে JavaScript দিয়ে validate করতে।
            """.trimIndent(),
            codeExample = """<form id="regForm">
  <!-- Required email -->
  <input type="email"
    placeholder="ইমেইল"
    required
    title="সঠিক ইমেইল দিন">
  
  <!-- Password with rules -->
  <input type="password"
    placeholder="পাসওয়ার্ড"
    minlength="8"
    maxlength="30"
    required>
  
  <!-- Phone (BD) -->
  <input type="tel"
    placeholder="01XXXXXXXXX"
    pattern="01[3-9][0-9]{8}"
    title="বাংলাদেশী নম্বর দিন"
    required>
  
  <!-- Age range -->
  <input type="number"
    placeholder="বয়স"
    min="13" max="120"
    required>
  
  <!-- URL -->
  <input type="url"
    placeholder="https://example.com">
  
  <!-- Date range -->
  <input type="date"
    min="2026-01-01"
    max="2026-12-31">
  
  <button type="submit">
    নিবন্ধন করুন
  </button>
</form>""",
            outputHtml = """<div style="background:#1e1e1e;padding:16px;border-radius:8px;color:white"><input type="email" placeholder="ইমেইল" style="width:100%;padding:10px;margin-bottom:8px;background:#2a2a2a;border:1px solid #E91E63;border-radius:6px;color:white;box-sizing:border-box"><input type="password" placeholder="পাসওয়ার্ড (কমপক্ষে ৮ অক্ষর)" style="width:100%;padding:10px;margin-bottom:8px;background:#2a2a2a;border:1px solid #555;border-radius:6px;color:white;box-sizing:border-box"><input type="tel" placeholder="01XXXXXXXXX" style="width:100%;padding:10px;margin-bottom:8px;background:#2a2a2a;border:1px solid #555;border-radius:6px;color:white;box-sizing:border-box"><button style="width:100%;padding:12px;background:#E91E63;color:white;border:none;border-radius:8px;font-size:16px;cursor:pointer">নিবন্ধন করুন</button></div>""",
            quizzes = listOf(
                Quiz("pattern attribute কী করে?",listOf("Style যোগ করে","Regular expression দিয়ে input format validate করে","Required করে","Placeholder দেখায়"),1,"pattern attribute regular expression দিয়ে input এর format check করে।"),
                Quiz("minlength='8' মানে কী?",listOf("সর্বোচ্চ ৮ character","কমপক্ষে ৮ character লিখতে হবে","৮ number","৮ line"),1,"minlength='8' মানে কমপক্ষে ৮ character না দিলে submit হবে না।")
            )
        )
    )
)

val allChapters = listOf(chapter1, chapter2, chapter3, chapter4, chapter5, chapter6, chapter7, chapter8)
