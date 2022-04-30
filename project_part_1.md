` `פרויקט מערכת טיסות

JAVA קורס

חלק א'  - ליבת המ  ערכת

![](Aspose.Words.04520e45-30b1-4396-85ef-6c416e4dcc84.001.png)

תיאור המערכת:

מערכת ניהול טיסות מאפשרת לחברות תעופה )Airline Companies( לפרסם טיסות וללקוחות לבחור את הטיסה המתאימה להם ביותר במחיר אטרקטיב  י.

REST API ממשק ,Business Logics שכבת ,)Database( המערכת תכלול בסיס נתונים (Front End) וצד לקו  ח

במסמך זה נתאר את השלב הראשון בבניית המערכת.

![](Aspose.Words.04520e45-30b1-4396-85ef-6c416e4dcc84.002.png)

במערכת הטיסות ישנם 4 סוגי משתמשים:

מנהל מערכת – Administrator .1 חברת תעופה המעוניינת לעדכן טיסות השייכות  לה – Airline Company .2 לקוח המעוניין לרכוש טיסה – Customer .3

גולש אנונימי שטרם נרשם לאתר - Anonymous .4

![](Aspose.Words.04520e45-30b1-4396-85ef-6c416e4dcc84.003.png)

**Database -שלב 1 - יצירת ה**

לצורך הפרויקט, ניצור את הסכמה הבאה: 

)הערה: הוסף not null constraint עבור הש  דות הרלוונט  יים(

![](Aspose.Words.04520e45-30b1-4396-85ef-6c416e4dcc84.004.jpeg)

![](Aspose.Words.04520e45-30b1-4396-85ef-6c416e4dcc84.005.png)![](Aspose.Words.04520e45-30b1-4396-85ef-6c416e4dcc84.006.png)

פירו  ט הטבלאות:

- טבלת **Airline\_Companies** מכילה את רשימת חברות התעו  פה.

לכל חברת תעופה יש מפתח מזהה )Id(, שם )Name(, קוד המדינה של החברה )Country\_Id(ומס' 

.)User\_Id ( משתמש

לכל חברת תעופה יש רשימה של טיסות אשר היא מפרסמת בכדי שלקוחות יקנו כרטיסים עב  ור הטיסה 

)Tickets וטבלת ,Flights ראה טבלת(

- טבלת **Flights** מכילה את רשימת הטיסות. בפרט  י הטיסה קיימים הפריטים הבאים: חברת התעופה אליה היא שייכת )Airline\_Company\_Id(, קוד מדינת המק  ור

זמן המראה ,(Destination\_Country\_Id) קוד מדינת היעד ,(Origin\_Country\_Id)  ומספר כרטיסים שנשארו(Landing\_Time) זמן נחיתה ,(Departure\_Time) 

.(Remaining\_Tickets)

` `,)Id(מכילה את רשימת הלקוחות הקיימים במאגר. לכל לקוח יש מפתח מזהה **Customers** טבלת  -

שם פרטי )First\_Name(, שם משפחה )Last\_Name(, כתובת )Address(, מספר 

.)  User\_Id ( ומס' משתמש )Credit\_Card\_No( מספר כרטיס אשראי )Phone\_No(טלפון

,)Id( מכילה את הכרטיסים שנרכשו עבור הטיסות. לכל כרטיס יש מפתח מזהה **Tickets** טבלת  -

.(Customer\_Id) ומספר לקוח (Flight\_Id)  מספר טיסה

שים לב שאותו הלקוח אינו יכול לרכוש פעמיים כרטיס לאותה הטיסה, לכן השילוב של מספר הטיסה 

- Unique ומספר הלקוח הוא
- טבלת **Countries** מייצגת את כל המדינות הקיימות במערכת. לכל מדינה יש מפתח מזהה 

` `אתגר: הוסף שדה של תמונה )דגל( לכל מדינה\* .)Name( ושם)Id(

,)Id( מכילה את כל המנהלים במערכת. לכל מנהל יש מפתח מזהה **Administrators** טבלת  -

.(User\_Id)  ומס' משתמש (Last\_Name) שם משפחה ,(First\_Name) שם פרטי

- טבלת **Users** מכילה את כל המשתמשים במערכת.

משתמשים אלו משויכים ללקוחות, חברות טיסה ומנהלי  ם.

לכל משתמש יש מפתח מזהה )Id(, שם משתמש ) Username(, סיסמא )Password(, כתובת אימ  ייל 

.)User\_Role ( וסיווג משתמש )Email(

השדה User\_Role ישמש כדי להבדיל בין לקוחות, חברות טיסה ומנהלים

)ראו טבלת User\_Roles(. \*אתגר: הוסף שדה של תמונה )thumbnail( לכל משתמש 

)Id( מכילה את סיווגי המשתמשים במערכת. לכל סיווג יש מפתח מזהה **User\_Roles** טבלת  -

.(Role\_Name) ושם סיו  וג

לתוך טבלה זו יש להוסיף שלושה סיווגים: לקוח, חברת טיסה ומנהל.

![](Aspose.Words.04520e45-30b1-4396-85ef-6c416e4dcc84.007.png)

לאחר יצירת כל הטבלאות, נוסיף מספר stored procedures ב-DB )נשתמש בהם בהמשך(: 

![](Aspose.Words.04520e45-30b1-4396-85ef-6c416e4dcc84.008.png)

**get\_airline\_by\_username(\_username text)![](Aspose.Words.04520e45-30b1-4396-85ef-6c416e4dcc84.009.png)**

פונקציה זו תשמש אותנו בעת ביצוע הלוגין, והיא תחזיר חברת תעופה )airline( לפי שם המשתמש ![](Aspose.Words.04520e45-30b1-4396-85ef-6c416e4dcc84.010.png)

)users-עם טבלת ה join -שלה. )רמז - השתמשו ב

**get\_customer\_by\_username(\_username text)![](Aspose.Words.04520e45-30b1-4396-85ef-6c416e4dcc84.011.png)**

פונקציה זו )בדומה לקודמת( תחזיר לקוח )customer( באמצעות שם המשתמש שלו.![](Aspose.Words.04520e45-30b1-4396-85ef-6c416e4dcc84.012.png)

**get\_user\_by\_username(\_username text)![](Aspose.Words.04520e45-30b1-4396-85ef-6c416e4dcc84.013.png)**

פונקציה זו תאפשר לנו לשלוף יוזרים מן ה-DB בצורה נוחה י  ותר.![](Aspose.Words.04520e45-30b1-4396-85ef-6c416e4dcc84.014.png)



|**get\_flights\_by\_parameters(\_origin\_counry\_id int, \_detination\_country\_id int,**|
| - |
|**\_date date)**|


|פונקציה זו תחזיר את כל הטיסות העונות על הפרמטרים הבאים: מס' מדינת מקור, מס' מדינת |
| - |
|יעדותאר  יך.|
**get\_flights\_by\_airline\_id(\_airline\_id bigint)![](Aspose.Words.04520e45-30b1-4396-85ef-6c416e4dcc84.015.png)**

פונקציה זו תחזיר את כל הטיסות השייכות לחברת התעופה![](Aspose.Words.04520e45-30b1-4396-85ef-6c416e4dcc84.016.png)

**get\_arrival\_flights(\_country\_id int)![](Aspose.Words.04520e45-30b1-4396-85ef-6c416e4dcc84.017.png)**

הפונקציה תחזיר את כל הטיסות הנוחתות ב-12 שעות הקרובות במדינה שניתנה![](Aspose.Words.04520e45-30b1-4396-85ef-6c416e4dcc84.018.png)

**get\_departure\_flights(\_country\_id int)![](Aspose.Words.04520e45-30b1-4396-85ef-6c416e4dcc84.019.png)**

הפונקציה תחזיר את כל הטיסות הממריאות ב-12 שעות הקרובות מן במדינה שניתנה ![](Aspose.Words.04520e45-30b1-4396-85ef-6c416e4dcc84.020.png)

**get\_tickets\_by\_customer(\_customer\_id bigint)![](Aspose.Words.04520e45-30b1-4396-85ef-6c416e4dcc84.021.png)**

הפונקציה תחזיר את כל כרטיסי הטיסה השייכים ללקוח הנתון![](Aspose.Words.04520e45-30b1-4396-85ef-6c416e4dcc84.022.png)

**Repository-שלב 2 - יצירת הפרויקט וה**

- Intellij-כעת יהיה עלינו ליצור את הפרויקט ב

בפרויקט יהיה קובץ config ובו נשמור את פרטי הגישה )jdbc( ל-DB שלנו. 

בשלב הבא נייצר מחלקת Repository שתטפל בהתממשקות מול ה-DB. לצורך כך, המחלקה תשתמשב-**Sql-alchemy**, כפי שראינו בכי  תה.

לפני כתיבת ה-Repository עצמו, יש ליצור את כל מחלקות ה-Models שבהן נעשה שימוש בפרו  יקט:

- Flight
- AirlineCompany ![](Aspose.Words.04520e45-30b1-4396-85ef-6c416e4dcc84.023.png)
- Customer 
- Administrator 
- User 
- Country
- Ticket

במחלקות הנ"ל נייצר שדות המייצגים Columns כפי שלמדנו.

- POCO כמו כן, על כל מחלקה לממש ממשק שנקרא

`  `DAO<T>  יורשים מאינטרפייס בשם- DAO כל במחלקות של ה- 

`  `T Get(int id);

`  `List GetAll();

`  `void Add(T t);

`  `void Remove(T t); void Update(T t);

בנוסף לפעולות ה- CRUD, יש להוסיף את הפונקציות הבאות :

- getAirlinesByCountry(country\_id)
- getFlightsByOriginCountryId(country\_id)
- getFlightsByDestinationCountryId(country\_id)
- getFlightsByDepartureDate(date)
- getFlightsByLandingDate(date)
- getFlightsByCustomer(customer)

לבסוף, במחלקות בהתאם  פונקציות שיקראו ל- stored procedure שיצרנו ב -DB קודם לכ  ן.

**Facades -שלב 3 - הוספת ה**

![](Aspose.Words.04520e45-30b1-4396-85ef-6c416e4dcc84.024.png) . Repository-שתתממשק עם ה Business Logics כעת נייצר שכבה בשם

כמחלקת בסיס תהיה לנו מחלקת FacadeBase << **אבסטרקטית >>**. אשר תכיל את הפונקצ  יות 

)Facades -הבאות: )המשותפות לכל ה

- get\_all\_flights()
- get\_flight\_by\_id(id)
- get\_flights\_by\_parameters(origin\_country\_id, destination\_country\_id, date)
- get\_all\_airlines()
- get\_airline\_by\_id(id)
- get\_airline\_by\_parameters( … )
- get\_all\_countries()
- get\_country\_by\_id(id)
- create\_new\_user ( user ) - for internal usage

עבור כל סוג משתמש עלינו ליצור מחלקת Facade. אלו יהיו ה-Facades שלנ  ו:

- AnonymousFacade
- CustomerFacade
- AirlineFacade
- AdministratorFacade

ראה דיאגרמה בעמוד הבא..  . 

![](Aspose.Words.04520e45-30b1-4396-85ef-6c416e4dcc84.025.jpeg)

הסבר: 

**:AnonymousFacade מחלקת**

*)תירש ממחלקת FacadeBase , ובנוסף יהיו בה הפונקציות הבאות(*

- login ( username, password )
- After a successful login the function will return the ***correct facade*** [customer, airline or admin] and a ***login-token*** member inside (@getter)
- Upon failure will return None or raise an error (i.e. WrongPasswordError)
- add\_customer ( … )

**:CustomerFacade מחלקת**

*)תירש מ-AnonymousFacade, ובנוסף יהיו בה הפונקציות הבאות(*

- update\_customer (customer)
- add\_ticket (ticket)
- remove\_ticket (ticket)
- get\_my\_tickets ()

**:AirlineFacade מחלקת**

*)תירש מ-AnonymousFacade, ובנוסף יהיו בה הפונקציות הבאות(*

- get\_my\_flights ()
- update\_airline (airline)
- add\_flight (flight)
- update\_flight (flight)
- remove\_flight (flight)

**:AdministratorFacade מחל  קת**

*)תירש מ-AnonymousFacade, ובנוסף יהיו בה הפונקציות הבאות(*

- get\_all\_customers()
- add\_airline (...)
- add\_customer (...)
- add\_administrator (...)
- remove\_airline (airline)
- remove\_customer (customer)
- remove\_administrator (administrator)
  - DBבכדי לגשת ל- DAO אמורות לפנות למחלקות ה- Façade שים לב שפונקציות ה- 

יש לאכוף את המידע המגיע לפונקציות ולדאוג שהערכים יהיו הגיוניים, לפני ביצוע ה  פעולה. לדוגמא

:

\-

- לא ניתן ליצור טיסה )flight( עם שדה remaining\_tickets שלילי )למשל 1-( 
- לא ניתן ליצור טיסה שבה זמן הנחיתה מתרחש לפני זמן ההמראה 
- לא ניתן ליצור טיסה שבה מדינת המקור ומדינת היעד הן אותה המדינה
- לא ניתן ליצור משתמש )user( עם סיסמא קצרה מ-6 תווים )\*רשות(
- איסור על חברת תעופה לערוך טיסה של חברה אחרת
- וע  וד…

**Login token - 4 שלב** 

בשלב זה ה-business logic שלנו כמעט מוכן, אך חסר בו רכיב קריטי ביותר. 

ה-Login token הינו אובייקט אשר נשתמש בו ב-facades השונים לצורך ביצוע פעולות מורשות  )כלומר פעולות שרק משתמשים רשומים יכולים לעשות(.

ה- LoginToken יוחזק כשדה בתוך ה Facade )ויועבר בבנאי   (

שלב א':

ניצור בפרויקט את מחלקת LoginToken ובה יהיו השדות: 

- id
- name
- role (customer/airline/admin)

AnonymousFacade-שכבר יצרנו ב login כעת ניגש לפונקציה

הפונקציה תקבל שם משתמש וסיסמא )כפרמטרים( ותבדוק האם הם תואמים למשתמש מסויים. אם כן - הפונקציה תחזיר את ה Facade המתאים עם האובייקט LoginToken )המכיל את פרט  י המשתמש(בתוכו 

שלב ב:'  

כעת ניגש לכל הפונקציות ב-facades הבאי  ם:

- CustomerFacade
- AirlineFacade
- AdminFacade
- token -ונוסיף להן שימוש ב

על כל פונקציה לבדוק שה-token תואם את המשתמש בטרם ביצוע ה  פעולה:

- כלומר במקרה של עדכון/מחיקה- הפונקציה תבדוק שאכן מדובר באותה יישות שהאייטם שייך לה: לדוגמא בפעולת ביטול כרטיס טיסה הפונקציה תבדוק שהכרטיס שייך לאותה לקוח 

token -המופיע ב

- האם מתאים לאותו FACADE )רשות(

כמו כן, באמצעות ה-token נאכוף את הבדיקה הבאה:

- חברת תעופה לא תוכל למחוק )או לערוך( טיסה שלא שייכת לה

**הערות:** 

- יש לכתוב קוד נקי ומסודר ולהקפיד על כללים כגון: שדות המתחילים באות קטנה, מחלקות באות גדולה  וכו'

`  `) PACKAGE( יש להפריד את המחלקות לקבצים נפרדים  -

- יש להוסיף שורת תיעוד סטנדרטי לכל פונקציה ומחלקה
- יש לעלות את הקוד ל- GIT ולשלוח קיש  ור

***requirements.txt*** -יש להוסיף את כל שמות הספריות שהשתמשתם בהם לקובץ ה  -

ב ה צ ל ח ה ! 

![](Aspose.Words.04520e45-30b1-4396-85ef-6c416e4dcc84.026.png)
