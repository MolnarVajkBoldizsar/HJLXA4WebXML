# xPathHJLXA4

A projekt a 8. gyakorlat három feladatát tartalmazza. A hallgatói és órarendi
feladatok azonos nevű Java osztályait külön forrásmappák tartják, ezért a
`run.ps1` egyszerre mindig csak a kiválasztott feladatot fordítja.

## Futtatás

Java 11 vagy újabb és Windows PowerShell szükséges. A parancsokat ebből a
mappából futtasd:

```powershell
.\run.cmd student-query
.\run.cmd student-modify
.\run.cmd schedule-query
.\run.cmd schedule-modify
```

Az első feladat ellenőrzi a `studentHJLXA4.xml` dokumentumot a
`studentHJLXA4.xsd` séma alapján, végrehajtja mind a 12 XPath-lekérdezést, és
az eredményeket a konzolra, valamint a `student1HJLXA4.xml`
fájlba írja. A hallgatómódosító az `01` azonosítójú hallgató keresztnevét
`HJLXA4`-re állítja, és csak ezt a hallgatót írja ki.

Az órarendi minta bemenete az `orarendHJLXA42.xml`. A lekérdező három XPath-
lekérdezést futtat, eredményeit konzolra és az
`orarendHJLXA4-query-results.xml` fájlba menti. A módosító három változtatást
végez (terem módosítása, megjegyzés hozzáadása és új óra felvétele), majd a
módosított dokumentumot konzolra és `orarendHJLXA41.xml` néven menti.
