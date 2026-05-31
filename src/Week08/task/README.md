# מטלת Week08 – Client/Server עם Observer, Builder, Facade, Prototype ו־SOLID

## מטרת המטלה

במטלה זו תמשיכו את מערכת ה־Client/Server מהשיעור, אך תשדרגו אותה למבנה תכנוני נכון יותר.
המטרה אינה רק לגרום לצ'אט לעבוד, אלא לבנות מערכת שקל להרחיב, לבדוק ולתחזק.

במטלה תשלבו:

1. עבודה עם `Socket` בצד לקוח ובצד שרת.
2. עבודה עם `Thread` עבור כמה לקוחות במקביל.
3. רישום משתמשים עם מזהה ייחודי.
4. שליחת הודעות במבנה דומה ל־HTTP: כותרות + גוף הודעה.
5. ניתוב הודעות פרטיות לפי מזהה יעד.
6. שימוש בתבנית Observer כבסיס למנגנון עדכון הלקוחות.
7. שימוש ב־Builder ליצירת אובייקטי הגדרות בצורה מסודרת.
8. שימוש ב־Facade כדי להסתיר מורכבות של הפעלת הלקוח והשרת.
9. שימוש ב־Prototype כדי ליצור הודעות לפי תבניות מוכנות.
10. שמירה על עקרונות SOLID.

---

## תיאור כללי של המערכת

המערכת היא מערכת צ'אט מבוססת טרמינל.

יש שרת אחד ומספר לקוחות.
כל לקוח שמתחבר צריך להזדהות באמצעות מזהה ייחודי, לדוגמה מספר טלפון.

לדוגמה:

```text
Enter your phone number: 0501234567
```

השרת צריך לבדוק האם כבר קיים משתמש מחובר עם אותו מזהה.

אם המזהה פנוי:

```text
Registration succeeded
```

אם המזהה תפוס:

```text
Identifier already exists. Try again.
```

יש לאפשר ללקוח מספר מוגבל של ניסיונות רישום, למשל 5 ניסיונות.
לאחר כישלון במספר הניסיונות המותר, השרת יסגור את החיבור.

---

## פורמט הודעה – Header + Body

במקום לשלוח רק טקסט פשוט, כל הודעה צריכה להיות בנויה משני חלקים:

1. Header – מידע על ההודעה.
2. Body – תוכן ההודעה עצמה.

הפורמט צריך להיות דומה ברעיון לבקשת HTTP.

דוגמה רעיונית להודעה פרטית:

```text
TYPE: PRIVATE_MESSAGE
FROM: 0501111111
TO: 0502222222
TITLE: hello
CONTENT_LENGTH: 17

Hello from David
```

החלק העליון הוא הכותרת.
החלק התחתון, אחרי שורה ריקה, הוא גוף ההודעה.

אין חובה לממש בדיוק את כל שדות הדוגמה, אבל חובה שלכל הודעה יהיו לפחות:

```text
TYPE
FROM
TO
TITLE
CONTENT_LENGTH
BODY
```

שדה `TO` יכול להכיל מזהה של משתמש יחיד, לדוגמה טלפון של משתמש אחר.
בעתיד אפשר להרחיב אותו גם לערכים כמו `ALL` עבור broadcast.

---

## דוגמה להתנהגות רצויה

נניח ששלושה לקוחות מחוברים:

```text
0501111111 - David
0502222222 - Yehuda
0503333333 - Sara
```

אם David רוצה לשלוח הודעה פרטית ל־Yehuda, הוא יזין בטרמינל את מזהה היעד ואת גוף ההודעה.
הלקוח יבנה הודעה עם כותרת וגוף.
השרת יקבל את ההודעה, יקרא מהכותרת את שדה `TO`, וינתח למי לשלוח אותה.

השרת לא אמור לשלוח את ההודעה לכל הלקוחות.
השרת צריך לשלוח אותה רק ללקוח שהמזהה שלו מופיע ב־`TO`.

---

## תבנית Observer – הרעיון המרכזי במטלה

תבנית Observer צריכה לשמש במערכת כמנגנון שמאפשר לשרת לעדכן לקוחות בלי להיות תלוי ישירות במימוש שלהם.

במקום שהשרת יעשה משהו כזה:

```java
client1.send(message);
client2.send(message);
client3.send(message);
```

השרת צריך לעבוד מול ממשקים.

הרעיון:

```text
ServerMessageSubject
        ↓ notify
MessageObserver
        ↓ update(message)
ClientSession
```

כל לקוח מחובר מיוצג בצד השרת על ידי אובייקט כמו `ClientSession`.
אותו `ClientSession` צריך להיות Observer, כלומר הוא יודע לקבל עדכון כאשר יש הודעה שמיועדת אליו.

השרת או רכיב הניתוב לא צריכים לדעת איך בפועל ההודעה נשלחת דרך Socket.
הם צריכים לדעת שיש Observer שאפשר לעדכן.

---

## למה Observer מתאים כאן?

Observer מתאים כי יש לנו אובייקטים שצריכים לקבל עדכונים כאשר מתרחש אירוע.

במערכת שלנו האירועים יכולים להיות:

1. משתמש חדש נרשם.
2. משתמש התנתק.
3. התקבלה הודעה פרטית.
4. התקבלה הודעת broadcast.
5. הודעה לא נשלחה כי היעד לא קיים.

במקום שהשרת יהיה אחראי ישירות על כל הלקוחות, השרת מחזיק Subject.
הלקוחות המחוברים נרשמים כ־Observers.
כאשר יש הודעה חדשה, ה־Subject מודיע ל־Observer המתאים.

---

## איך לשלב Observer במטלה

עליכם לממש ממשק:

```java
MessageObserver
```

שמייצג אובייקט שיכול לקבל הודעה.

עליכם לממש ממשק:

```java
MessageSubject
```

שמייצג אובייקט שמנהל Observers ויכול לעדכן אותם.

בצד השרת:

```text
ClientSession implements MessageObserver
```

כלומר כל חיבור של לקוח הוא Observer.
כאשר השרת רוצה לשלוח הודעה ללקוח מסוים, הוא לא פונה ישירות ל־Socket שלו.
הוא מבקש מה־Subject לעדכן את ה־Observer המתאים לפי מזהה המשתמש.

לדוגמה רעיונית:

```text
MessageRouter receives message
MessageRouter reads TO header
MessageRouter asks Subject to notify the observer with that identifier
ClientSession receives update(message)
ClientSession sends message to socket
```

---

## רישום משתמש עם מזהה ייחודי

כאשר לקוח מתחבר לשרת, אסור לו להתחיל לשלוח הודעות לפני רישום.

תהליך הרישום:

1. השרת מקבל חיבור חדש.
2. השרת מבקש מהלקוח מזהה, למשל מספר טלפון.
3. הלקוח שולח הודעת רישום.
4. השרת בודק האם המזהה כבר קיים.
5. אם המזהה פנוי, השרת רושם את הלקוח.
6. אם המזהה תפוס, השרת מבקש ניסיון נוסף.
7. אחרי 5 ניסיונות כושלים, השרת סוגר את החיבור.

המזהה הייחודי ישמש בהמשך לניתוב הודעות.

לדוגמה:

```text
TO: 0502222222
```

השרת ימצא את ה־Observer שהמזהה שלו הוא `0502222222` וישלח אליו את ההודעה.

---

## Builder

יש לשמור את אותה דרישת Builder מהמטלה הקודמת.

חובה לממש Builder עבור:

```text
ClientConfig
ServerConfig
```

`ClientConfig` צריך להכיל לפחות:

```text
host
port
```

אפשר להוסיף:

```text
maxRegistrationAttempts
```

`ServerConfig` צריך להכיל לפחות:

```text
port
maxClients
maxRegistrationAttempts
```

דרישות Builder:

1. אין ליצור את אובייקטי הקונפיגורציה עם בנאי ציבורי רגיל.
2. יש להשתמש ב־static inner class בשם `Builder`.
3. יש לאפשר שרשור מתודות.
4. יש לבצע בדיקות תקינות בסיסיות ב־`build`.
5. יש להחזיר אובייקט immutable ככל האפשר.

דוגמה לשימוש רצוי, בלי לממש כאן את הפתרון:

```java
ServerConfig config = ServerConfig.builder()
        .port(8010)
        .maxClients(10)
        .maxRegistrationAttempts(5)
        .build();
```

---

## Facade

יש להשתמש ב־Facade כדי לפשט את ההפעלה של הלקוח והשרת.

בצד השרת צריכה להיות מחלקה כמו:

```text
ChatServerFacade
```

התפקיד שלה:

1. לקבל `ServerConfig`.
2. ליצור את הרכיבים הפנימיים של השרת.
3. להפעיל את השרת.
4. לעצור את השרת בצורה מסודרת.

בצד הלקוח צריכה להיות מחלקה כמו:

```text
ChatClientFacade
```

התפקיד שלה:

1. לקבל `ClientConfig`.
2. להתחבר לשרת.
3. לבצע רישום משתמש.
4. להפעיל Thread שמאזין להודעות מהשרת.
5. לקרוא קלט מהטרמינל.
6. לשלוח הודעות לשרת.
7. לסגור משאבים בסיום.

המטרה של Facade היא ש־`main` יהיה קצר ופשוט.

לדוגמה רעיונית:

```java
public static void main(String[] args) {
    ClientConfig config = ClientConfig.builder()
            .host("localhost")
            .port(8010)
            .build();

    new ChatClientFacade(config).start();
}
```

---

## Prototype

יש לשלב Prototype בצורה פשוטה ולא מסובכת.

הרעיון הוא שלא בכל פעם שנרצה ליצור הודעה נבנה אותה מאפס.
במקום זאת, נגדיר תבניות הודעה בסיסיות ונשכפל אותן.

לדוגמה רעיונית לתבניות:

```text
REGISTER message prototype
PRIVATE_MESSAGE prototype
BROADCAST_MESSAGE prototype
ERROR message prototype
DISCONNECT message prototype
```

כאשר לקוח רוצה לשלוח הודעה פרטית:

1. הוא מבקש מה־registry תבנית של `PRIVATE_MESSAGE`.
2. הוא משכפל אותה.
3. הוא ממלא `FROM`, `TO`, `TITLE` ו־`BODY`.
4. הוא שולח אותה לשרת.

אין צורך להפוך את Prototype למורכב מדי.
המטרה היא להראות הבנה של הרעיון:

```text
יש אובייקט בסיסי מוכן
משכפלים אותו
משנים רק את הנתונים הספציפיים
```

---

## מבנה מחלקות מומלץ

```text
src/main/java/week08
│
├── common
│   ├── Message.java
│   ├── MessageHeader.java
│   ├── MessageType.java
│   ├── MessageParser.java
│   ├── TextMessageParser.java
│   ├── Prototype.java
│   └── MessagePrototypeRegistry.java
│
├── observer
│   ├── MessageObserver.java
│   └── MessageSubject.java
│
├── client
│   ├── ClientMain.java
│   ├── ClientConfig.java
│   ├── ChatClientFacade.java
│   ├── ClientConnection.java
│   ├── SocketClientConnection.java
│   ├── ServerListener.java
│   └── ClientMessageFactory.java
│
└── server
    ├── ServerMain.java
    ├── ServerConfig.java
    ├── ChatServerFacade.java
    ├── ClientSession.java
    ├── ClientRegistry.java
    ├── InMemoryClientRegistry.java
    ├── ServerMessageSubject.java
    ├── MessageRouter.java
    └── RegistrationService.java
```

---

## חלוקת אחריות לפי SOLID

### Single Responsibility Principle

כל מחלקה צריכה לעשות דבר אחד בלבד.

דוגמאות:

```text
TextMessageParser       אחראי רק להמרה בין טקסט לאובייקט Message
MessageRouter           אחראי רק לניתוב הודעות
ClientSession           אחראי רק לניהול חיבור של לקוח אחד בצד השרת
ClientRegistry          אחראי רק לשמירת לקוחות מחוברים
ServerMessageSubject    אחראי רק להודעה ל־Observers
ChatServerFacade        אחראי רק להפעלת השרת ברמה גבוהה
```

### Open/Closed Principle

יש לתכנן כך שאפשר להוסיף סוגי הודעות חדשים בלי לשבור את כל הקוד.
לדוגמה, בעתיד אפשר להוסיף:

```text
GROUP_MESSAGE
FILE_MESSAGE
TYPING_NOTIFICATION
```

### Liskov Substitution Principle

כל מימוש של `MessageObserver` צריך להיות ניתן לשימוש בכל מקום שמצפה ל־`MessageObserver`.

### Interface Segregation Principle

אין ליצור ממשק ענק אחד שעושה הכול.
יש להפריד בין:

```text
ClientConnection
MessageObserver
MessageSubject
MessageParser
ClientRegistry
```

### Dependency Inversion Principle

מחלקות גבוהות לא צריכות להיות תלויות ישירות ב־`Socket`.
לדוגמה, `ChatClientFacade` צריך לעבוד מול `ClientConnection`, ולא ישירות מול `Socket`.

---

## דרישות פונקציונליות

### צד שרת

עליכם לממש שרת שיכול:

1. להיפתח על פורט לפי `ServerConfig`.
2. לקבל כמה לקוחות במקביל.
3. ליצור Thread נפרד לכל לקוח.
4. לדרוש רישום מזהה ייחודי מכל לקוח.
5. למנוע שני לקוחות עם אותו מזהה.
6. לשמור את הלקוחות המחוברים ב־Registry.
7. לרשום כל לקוח כ־Observer.
8. לקבל הודעות מהלקוחות.
9. לקרוא את הכותרת של ההודעה.
10. לנתב הודעה פרטית לפי שדה `TO`.
11. לשלוח שגיאה אם היעד לא קיים.
12. להסיר לקוח שהתנתק.
13. לסגור משאבים בצורה תקינה.

### צד לקוח

עליכם לממש לקוח שיכול:

1. להתחבר לשרת לפי `ClientConfig`.
2. לבקש מהמשתמש מזהה ייחודי דרך הטרמינל.
3. לשלוח הודעת רישום לשרת.
4. לקבל אישור או דחייה מהשרת.
5. לפתוח Thread שמאזין להודעות מהשרת.
6. לקבל מהמשתמש יעד, כותרת וגוף הודעה.
7. לבנות הודעה עם Header ו־Body.
8. לשלוח הודעה לשרת.
9. לאפשר יציאה מסודרת, למשל באמצעות `exit`.
10. לסגור Socket ומשאבים בסיום.

---

## הנחיות לקלט בטרמינל

כדי לא להקשות, אין צורך לבנות UI נפרד.
הכול יכול להתבצע דרך `Scanner` ו־`System.out.println`.

דוגמה אפשרית בצד לקוח:

```text
Enter your phone number:
0501111111

Enter target phone:
0502222222

Enter title:
hello

Enter message:
Hi Yehuda, how are you?
```

הלקוח צריך להפוך את הנתונים האלה להודעה במבנה Header + Body ולשלוח אותה לשרת.

---

## Threadים

### בצד השרת

כל לקוח שמתחבר צריך לקבל Thread משלו.

```text
Client A → ClientSession Thread
Client B → ClientSession Thread
Client C → ClientSession Thread
```

ה־Thread אחראי לקרוא הודעות מאותו לקוח ולהעביר אותן ל־MessageRouter.

### בצד הלקוח

בצד הלקוח צריך להיות Thread שמאזין להודעות מהשרת.

למה?
כי הלקוח צריך במקביל:

1. לקלוט קלט מהמשתמש.
2. לקבל הודעות מהשרת.

לכן צד הלקוח צריך לפחות שני נתיבי ביצוע:

```text
Main thread       קורא קלט מהמשתמש ושולח הודעות
Listener thread   מאזין להודעות נכנסות מהשרת
```

---

## דגשים חשובים

1. אין לשים את כל הקוד במחלקת `Client` אחת או `Server` אחת.
2. אין לעבוד ישירות עם `Socket` מתוך `main`.
3. אין לשלוח הודעה פרטית על ידי מעבר ידני על כל הלקוחות מתוך מחלקת השרת הראשית.
4. יש להשתמש ב־Observer כדי להפריד בין ניתוב הודעה לבין שליחת הודעה בפועל.
5. יש להקפיד על סגירת `Socket`, `Reader` ו־`Writer`.
6. יש לטפל בניתוק לקוח בלי להפיל את השרת.
7. יש לטפל במקרה שבו לקוח שולח הודעה ליעד שלא קיים.
8. יש לתעד ב־README שלכם איפה השתמשתם בכל תבנית עיצוב.

---

## מה להגיש

יש להגיש פרויקט Java הכולל:

1. כל קבצי המקור.
2. README קצר שלכם.
3. הסבר איפה מופיעות התבניות:
   - Observer
   - Builder
   - Facade
   - Prototype
4. צילום או תיאור הרצה עם לפחות שני לקוחות.
5. דוגמה לשליחת הודעה פרטית מוצלחת.
6. דוגמה לניסיון רישום עם מזהה שכבר קיים.
7. דוגמה לשליחת הודעה ליעד שלא קיים.

---

## בדיקות חובה

עליכם לבדוק לפחות את המקרים הבאים:

### בדיקה 1 – התחברות לקוח יחיד

1. מריצים שרת.
2. מריצים לקוח.
3. מזינים מזהה חדש.
4. השרת מאשר רישום.

### בדיקה 2 – מניעת מזהה כפול

1. מריצים שרת.
2. מריצים לקוח עם מזהה `0501111111`.
3. מריצים לקוח נוסף עם אותו מזהה.
4. השרת דוחה את הרישום השני.

### בדיקה 3 – הודעה פרטית

1. מריצים שני לקוחות עם מזהים שונים.
2. לקוח א' שולח הודעה ללקוח ב'.
3. רק לקוח ב' מקבל את ההודעה.

### בדיקה 4 – יעד לא קיים

1. לקוח שולח הודעה למזהה שלא מחובר.
2. השרת מחזיר הודעת שגיאה לשולח.

### בדיקה 5 – ניתוק לקוח

1. לקוח מתחבר.
2. הלקוח כותב `exit`.
3. השרת מסיר אותו מה־Registry ומה־Observers.
4. השרת ממשיך לעבוד עבור לקוחות אחרים.

---

## הערה חשובה

הקבצים המצורפים הם תבניות בלבד.
הם כוללים מחלקות, ממשקים, חתימות מתודות ו־TODO.
עליכם להשלים את המימוש בעצמכם.

אין חובה לממש את המערכת בדיוק באותו אופן, אבל חובה לשמור על הרעיונות המרכזיים:

```text
Observer לניתוב ועדכון לקוחות
Builder ליצירת הגדרות
Facade להפעלה פשוטה
Prototype ליצירת הודעות מתבניות
Header + Body לכל הודעה
רישום משתמשים עם מזהה ייחודי
עמידה בעקרונות SOLID
```
