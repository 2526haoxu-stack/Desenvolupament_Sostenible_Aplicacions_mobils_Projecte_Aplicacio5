# Cookly – Activitat 06: Menú i llistes

En aquesta activitat hem afegit a la nostra app de receptes, Cookly, hem afegit una pantalla inicial 
amb animació, un menú per anar d'una pantalla a una altra i llistes amb RecyclerView.

- **Fet per:** Hao Xu i Stanislav Kosyan
- **Vídeo:** [link](https://drive.google.com/file/d/1t0IGGeuvzpyFhg8I2SrFBqT0pbxe4qIu/view?usp=drive_link)

## 1. Pantalla inicial (splash)
Primer per fer l'animació vam crear una activity pròpia animacionActivity, i l'hem marcat com a "launcher" 
a l'androidManifest en lloc de MainActivity, per a que sigui la app interpreti aquesta com a punt d'entrada.

## 2. Menú de navegació

EL nostre MainActivity fa servir dos menus i cadascun d'aquells fa servir una tècnica diferent i ara els veurem:

  a. Toolbar i menú desplegable
Com que el tema de l'aplicació no té action bar pròpia vem fer servi una Toolbar al layout i el vam activar amb setSupportActionBar().
Després vam definir les opcions necessàries dins del main_appbar.xml i en la funció de onOptionsItemSelected vam fer que miri l'itemId i obri 
amb un intent la pantalla corresponent.
  b. Bottom Navigation Bar
Al principi definim les pestanyes dins dels bottom_nav_menu.xml i els posem en una etiqueta de BottomNavigationView.
Després li vam posar tot dins d'un FragmentContainerView quina actua quan es prem una pestanya amb la funció setOnItemSelectedListener fa un when sobre l'itemId i substitueix el fragment amb el escollit:

Per altre part vam fer que el fragment inicial només es carregui si savedInstanceState és null. Això fa que en cas de que es recrei l'activity per exemple girant la pantalla no es tornarà a afegir un segon fragment.

## 3. RecyclerView: Adapter, Holder i dades

### Llista de receptes (firstFragment)

Classe                  |      Què fa 
----------------------------------------------------------------------------------------------------------------------------
Receta                  |      És una data class amb les dades d'una recepta que conté: el nom, la descripció, si és vegana, si porta gluten, el seu país i la seva imatge. 
DataSourceRecetas       |      És l'objecte amb una llista de les receptes. 
Adapter                 |      Rep la llista i una funció onItemClick. A en onCreateViewHolder genera l'item.xml i a getItemCount retorna la mida de la llista i a onBindViewHolder passa cada recepta al holder. 
ViewHolder              |      Aquesta classe guarda les referències dins de les files de la vista per no haver-les de buscar a cada fila. Per altra part la funció mostrarReceta() les omple amb les dades i connecta el botó amb onItemClick. 

El RecycleView fa servir un grid layout manager de 2 columnes. Li vam passar el clic com a funció lambda a l'adapter, en lloc de programar-lo dins del holder, per a que sigui el fragment qui decideix què passarà quan premi el botó. 
En aquest cas mostra un toast amb el nom de la recepta.

## 4. Accessibilitat i usabilitat

Per millorar el codi i la seva part d'accessiblitat vam fer el següent:

- Vam posar descripcions a totes les imatges i botons de les finestres i afegir els textos a strings.xml.
- Vam posar el tema amb colors propis dins de values i values-night, per tenir mode clar i fosc.
- Vam posar mides de text en sp en comptes d'dp, per a que es respectin la mida de les lletres del sistema d'usuair.
- Hem posar al bottom menu una icona i text per a que s'entengui bé que fa cada cosa.
- Vam posar els colors i icones a colors.xml, themes.xml i al drawable.
