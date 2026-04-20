package com.htmllearn.app.data.content

import com.htmllearn.app.data.model.*

val chapter2 = Chapter(
    id = "ch2", title = "Text ও Headings", description = "Text formatting এবং heading tags",
    color = 0xFF00BCD4,
    lessons = listOf(
        Lesson(
            id = "ch2_l1", chapterId = "ch2", title = "Heading Tags (h1-h6)", xp = 25, duration = "৬ মিনিট",
            explanation = """
HTML এ ৬ ধরনের heading tag আছে — h1 থেকে h6।

📌 h1 সবচেয়ে বড়, h6 সবচেয়ে ছোট।

h1 — মূল শিরোনাম (Page title)
h2 — প্রধান বিভাগ
h3 — উপ-বিভাগ
h4 — ছোট বিভাগ
h5 — আরো ছোট
h6 — সবচেয়ে ছোট

📌 SEO এর জন্য গুরুত্বপূর্ণ:
প্রতি page এ শুধু একটি h1 থাকা উচিত।
Google search engine heading দেখে page বোঝে।

📌 ব্যবহার:
Design এর জন্য নয়, structure এর জন্য heading ব্যবহার করো।
CSS দিয়ে পরে design করা যাবে।
            """.trimIndent(),
            codeExample = """<h1>প্রধান শিরোনাম (h1)</h1>
<h2>বিভাগ শিরোনাম (h2)</h2>
<h3>উপ-বিভাগ (h3)</h3>
<h4>ছোট শিরোনাম (h4)</h4>
<h5>আরো ছোট (h5)</h5>
<h6>সবচেয়ে ছোট (h6)</h6>""",
            outputHtml = "<h1 style='color:#6C63FF;margin:4px 0'>প্রধান শিরোনাম</h1><h2 style='color:#00BCD4;margin:4px 0'>বিভাগ শিরোনাম</h2><h3 style='margin:4px 0'>উপ-বিভাগ</h3><h4 style='margin:4px 0'>ছোট শিরোনাম</h4><h5 style='margin:4px 0'>আরো ছোট</h5><h6 style='margin:4px 0'>সবচেয়ে ছোট</h6>",
            quizzes = listOf(
                Quiz("HTML এ কয়টি heading level আছে?",listOf("৪টি","৫টি","৬টি","৮টি"),2,"HTML এ h1 থেকে h6 পর্যন্ত মোট ৬টি heading level।"),
                Quiz("প্রতি page এ কতটি h1 থাকা উচিত?",listOf("যতটা চাই","শুধু একটি","দুটি","তিনটি"),1,"SEO এর জন্য প্রতি page এ শুধু একটি h1 ব্যবহার করা উচিত।")
            )
        ),
        Lesson(
            id = "ch2_l2", chapterId = "ch2", title = "Paragraph ও Text Tags", xp = 30, duration = "৮ মিনিট",
            explanation = """
📌 <p> — Paragraph:
Text এর সবচেয়ে মূল ব্যবহার। Browser automatically উপরে-নিচে space যোগ করে।

📌 <br> — Line Break:
নতুন লাইনে যাওয়ার জন্য। Self-closing tag।

📌 <hr> — Horizontal Rule:
Horizontal line দেখায়। বিভাগ আলাদা করতে ব্যবহার হয়।

📌 Text Formatting Tags:
<strong> — গুরুত্বপূর্ণ text (bold দেখায়)
<em> — জোর দেওয়া text (italic দেখায়)
<b> — শুধু bold (কোনো অর্থ নেই)
<i> — শুধু italic
<u> — underline
<s> — strikethrough (কাটা)
<mark> — highlighted text
<small> — ছোট text
<sup> — superscript (উপরে)
<sub> — subscript (নিচে)
<code> — code দেখাতে
<pre> — preformatted text
            """.trimIndent(),
            codeExample = """<p>এটি একটি <strong>গুরুত্বপূর্ণ</strong> 
paragraph।</p>

<p>এটি <em>ইটালিক</em> এবং 
<u>underline</u> text।</p>

<p>পানির সূত্র: H<sub>2</sub>O</p>
<p>বর্গফুট: m<sup>2</sup></p>

<p><mark>এটি highlighted।</mark></p>
<p><s>এটি বাতিল হয়েছে।</s></p>

<code>console.log("Hello")</code>

<pre>
  এই text
    এভাবেই
  দেখাবে
</pre>""",
            outputHtml = """<p>এটি একটি <strong>গুরুত্বপূর্ণ</strong> paragraph।</p><p>পানির সূত্র: H<sub>2</sub>O | বর্গফুট: m<sup>2</sup></p><p><mark style="background:#FFD700;color:#000;padding:2px 4px;border-radius:3px">এটি highlighted।</mark></p><p><s>এটি বাতিল হয়েছে।</s></p><code style="background:#1e1e1e;padding:4px 8px;border-radius:4px;font-family:monospace">console.log("Hello")</code>""",
            quizzes = listOf(
                Quiz("<strong> এবং <b> এর পার্থক্য কী?",listOf("কোনো পার্থক্য নেই","<strong> semantic, <b> শুধু visual","<b> semantic, <strong> visual","দুটোই একই কাজ করে"),1,"<strong> semantic — content গুরুত্বপূর্ণ বোঝায়। <b> শুধু bold দেখায়।"),
                Quiz("H₂O লিখতে '2' কোন tag দিয়ে?",listOf("<sup>","<sub>","<small>","<code>"),1,"H<sub>2</sub>O — subscript এর জন্য <sub> tag।")
            )
        ),
        Lesson(
            id = "ch2_l3", chapterId = "ch2", title = "Lists — ul, ol, dl", xp = 30, duration = "৮ মিনিট",
            explanation = """
HTML এ ৩ ধরনের list আছে:

📌 Unordered List (<ul>):
বুলেট point সহ list।
প্রতিটি item <li> tag এ।

📌 Ordered List (<ol>):
numbered list — 1, 2, 3...
type attribute দিয়ে style বদলানো যায়:
type="A" → A, B, C
type="a" → a, b, c
type="I" → I, II, III
type="i" → i, ii, iii

📌 Description List (<dl>):
শব্দ এবং তার ব্যাখ্যার list।
<dt> — term (শব্দ)
<dd> — description (ব্যাখ্যা)

📌 Nested List:
list এর ভেতরে আরেকটি list।
            """.trimIndent(),
            codeExample = """<!-- Unordered List -->
<ul>
  <li>HTML</li>
  <li>CSS</li>
  <li>JavaScript
    <ul>
      <li>React</li>
      <li>Vue</li>
    </ul>
  </li>
</ul>

<!-- Ordered List -->
<ol type="A">
  <li>প্রথম ধাপ</li>
  <li>দ্বিতীয় ধাপ</li>
  <li>তৃতীয় ধাপ</li>
</ol>

<!-- Description List -->
<dl>
  <dt>HTML</dt>
  <dd>ওয়েবপেজের ভাষা</dd>
  <dt>CSS</dt>
  <dd>ডিজাইনের ভাষা</dd>
</dl>""",
            outputHtml = """<ul style="color:white"><li>HTML</li><li>CSS</li><li>JavaScript<ul><li>React</li><li>Vue</li></ul></li></ul><ol type="A" style="color:white"><li>প্রথম ধাপ</li><li>দ্বিতীয় ধাপ</li></ol><dl style="color:white"><dt style="color:#6C63FF;font-weight:bold">HTML</dt><dd>ওয়েবপেজের ভাষা</dd><dt style="color:#6C63FF;font-weight:bold">CSS</dt><dd>ডিজাইনের ভাষা</dd></dl>""",
            quizzes = listOf(
                Quiz("<ul> কী?",listOf("Ordered list","Unordered list","Description list","Numbered list"),1,"<ul> = Unordered List — বুলেট point সহ list।"),
                Quiz("<ol type='I'> কী দেখাবে?",listOf("1, 2, 3","A, B, C","I, II, III","i, ii, iii"),2,"type='I' দিয়ে Roman numeral uppercase দেখায়।")
            )
        ),
        Lesson(
            id = "ch2_l4", chapterId = "ch2", title = "Links — Anchor Tag", xp = 35, duration = "৯ মিনিট",
            explanation = """
<a> tag দিয়ে link তৈরি করা হয়।

📌 href attribute:
Link এর destination।
• External: https://website.com
• Internal: about.html
• Section: #section-id
• Email: mailto:email@example.com
• Phone: tel:+8801700000000

📌 target attribute:
_self — same tab (default)
_blank — নতুন tab
_parent — parent frame
_top — full window

📌 Download Link:
download attribute যোগ করলে file download হয়।

📌 Anchor (Section Link):
Page এর ভেতরে specific অংশে যাওয়া।
destination element এ id দিতে হবে।
link এ #id লিখতে হবে।
            """.trimIndent(),
            codeExample = """<!-- External Link -->
<a href="https://google.com" 
   target="_blank">
  Google খুলুন
</a>

<!-- Email Link -->
<a href="mailto:info@example.com">
  Email পাঠান
</a>

<!-- Phone Link -->
<a href="tel:+8801700000000">
  কল করুন
</a>

<!-- Section Link -->
<a href="#contact">Contact এ যান</a>

<!-- Download -->
<a href="file.pdf" download>
  PDF ডাউনলোড করুন
</a>

<section id="contact">
  <h2>Contact Section</h2>
</section>""",
            outputHtml = """<a href="https://google.com" target="_blank" style="color:#6C63FF;display:block;margin:4px 0">Google খুলুন ↗</a><a href="mailto:info@example.com" style="color:#00BCD4;display:block;margin:4px 0">✉ Email পাঠান</a><a href="tel:+8801700000000" style="color:#4CAF50;display:block;margin:4px 0">📞 কল করুন</a>""",
            quizzes = listOf(
                Quiz("নতুন tab এ link খুলতে কোন attribute?",listOf("target='new'","target='tab'","target='_blank'","target='open'"),2,"target='_blank' link নতুন tab এ খোলে।"),
                Quiz("Email link এর href কেমন হবে?",listOf("email:address","send:address","mailto:address","mail:address"),2,"Email link: href='mailto:your@email.com'")
            )
        ),
        Lesson(
            id = "ch2_l5", chapterId = "ch2", title = "Images — img Tag", xp = 35, duration = "১০ মিনিট",
            explanation = """
<img> tag দিয়ে image দেখানো হয়। এটি self-closing tag।

📌 src attribute:
Image এর path বা URL।
• Local: images/photo.jpg
• External: https://example.com/img.jpg

📌 alt attribute:
Image না দেখালে এই text দেখায়।
Screen reader ব্যবহারকারীরা এটি শুনতে পায়।
SEO এর জন্য গুরুত্বপূর্ণ।
অবশ্যই দিতে হবে!

📌 width ও height:
Pixel বা percentage এ দেওয়া যায়।
CSS দিয়ে control করা বেশি ভালো।

📌 Image Formats:
JPEG — ছবি (compressed)
PNG — transparency সহ ছবি
GIF — animated image
SVG — scalable vector
WebP — modern, small size

📌 Responsive Image:
style="max-width:100%" দিলে mobile friendly হয়।
            """.trimIndent(),
            codeExample = """<!-- Basic Image -->
<img src="photo.jpg" 
     alt="আমার ছবি"
     width="300">

<!-- Responsive Image -->
<img src="banner.jpg" 
     alt="Banner"
     style="max-width:100%;
            height:auto;
            border-radius:12px">

<!-- External Image -->
<img src="https://via.placeholder.com/200x100"
     alt="Placeholder"
     width="200" height="100">

<!-- Image with Link -->
<a href="detail.html">
  <img src="thumb.jpg" 
       alt="Click করুন"
       width="150">
</a>

<!-- Figure with Caption -->
<figure>
  <img src="chart.jpg" 
       alt="Chart">
  <figcaption>চার্টের ক্যাপশন</figcaption>
</figure>""",
            outputHtml = """<div style="background:#1e1e1e;padding:12px;border-radius:8px;color:white"><p style="color:#6C63FF">🖼 Image এর উদাহরণ</p><div style="background:#2a2a2a;width:200px;height:120px;border-radius:8px;display:flex;align-items:center;justify-content:center;margin:8px 0"><span style="color:#666">200 × 120 Image</span></div><figcaption style="color:#999;font-size:13px">চার্টের ক্যাপশন</figcaption></div>""",
            quizzes = listOf(
                Quiz("alt attribute কেন দেওয়া জরুরি?",listOf("Image বড় করে","Accessibility ও SEO এর জন্য","Color বদলায়","Speed বাড়ায়"),1,"alt দেওয়া জরুরি — accessibility ও SEO এর জন্য।"),
                Quiz("Responsive image করতে কোন CSS?",listOf("width:fixed","max-width:100%;height:auto","min-width:100%","width:100vw"),1,"max-width:100%;height:auto দিলে image responsive হয়।")
            )
        )
    )
)
