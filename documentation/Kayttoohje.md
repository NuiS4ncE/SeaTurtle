# Asennus- ja käyttöohje

## Ohjelman käynnistäminen ja käyttäminen

Hae viimeisin julkaistu versio ohjelmasta GitHubin releaseista - linkki löytyy [README.md](https://github.com/NuiS4ncE/SeaTurtle/blob/main/README.md):stä. Mene hakemistoon, johon JAR-tiedoston latasit, ja käynnistä ohjelma komennolla:

`java -jar SeaTurtle-all.jar`

Jos ajettavan JAR-paketin nimi on joku muu, korvaa _SeaTurtle-all.jar_ oikealla tiedostonimellä.

Ohjelman käynnistyessä näytetään käytettävissä olevat komennot. Voit listata käytettävissä olevat komennot kommennolla __h__.

Voit poistua ohjelmasta komennolla __q__.

## Vinkkien listaaminen

Ohjelmaan talletetut kirja- ja artikkelivinkit listataan komennolla __l__.

## Vinkin lisääminen

### Kirjavinkki

Uusi kirjavinkki lisätään komennolla __k__. Ohjelma pyytää syöttämään kirjan nimen, kirjoittajan ja sivumäärän. Kirjan nimi on pakollinen, muut tiedot vapaaehtoisia.

Halutessasi voit lisätä kirjalle myös kirjanmerkin ja/tai tageja.

Lisäämisen jälkeen ohjelma listaa kaikki kirjavinkit. Voit lisätä uuden kirjan komennolla __k__ tai palata päävalikkoon komennolla __v__.

### Artikkelivinkki 

Uusi artikkelivinkki lisätään komennolla __a__. Ohjelma pyytää syöttämään artikkelin otsikon ja URL-osoitteen. Artikkelin nimi on pakollinen, mutta URL-osoite on vapaaehtoinen.

Halutessasi voit lisätä artikkelilla myös tageja.

Lisäämisen jälkeen ohjelma listaa kaikki artikkelivinkit. Voit lisätä uuden artikkelin komennolla __a__ tai palata päävalikkoon komennolla __v__.


## Kirjavinkin kirjanmerkin lisääminen/muokkaaminen

Kirjavinkille voi lisätä kirjanmerkin tai muokata olemassaolevaa kirjanmerkkiä komenolla __m__. Ohjelma pyytää valitsemaan päivitettävän kirjan numeron listasta ja syöttämään kirjanmerkin sivunumeron. Osuus, joka kirjasta on luettu päivittyy automaattisesti.

## Lukuvinkin hakeminen

Ohjelmaan talletettuja vinkkejä voi etsiä komennolla __e__.

Haun voi kohistaa vain kirjoihin (__k__), vain artikkeleihin (__a__) taikka molempiin (__ak__). Vaihtoehtoisesti haun voi kohdistaa myös tageihin (__t__).

Haku tehdään antamalla haluttu hakutermi. Pääset takaisin eri hakuvaihtoehtoihin antamalla tyhjän hakutermin.

Päävalikkoon pääset takaisin komennolla __v__.

### Kirjavinkki

Hakutulos näyttää kaikki kirjavinkit, joiden nimi sisältää hakutermin. Esimerkiksi hakutermi "tietokon" palauttaa vinkit, joiden nimet sisältävät mm. sanat "tietokoneen", "Tietokoneiden" ja "tietokoneista". 

### Artikkelivinkki

Hakutulos näyttää kaikki artikkelivinkit, joiden otsikko sisältää hakutermin. Hakemisperusteet ovat samat kuin kirjavinkillä. Jos yhtään artikkeliotsikkoa ei löydy, suoritetaan haku uudestaan artikkelien URL-osoitteilla. Hakutermi "f" palauttaa tällöin artikkelivinkit, joiden osoitteet sisältävät merkin "f". Esimerkiksi "https://www.worldwildlife.org/species/sea-turtle" ja "www.hy.fi".

## Tagien hallinta

Pääset muokkaamaan tageja komennolla __t__. Valitse tämän jälkeen, haluatko muokata kirjojen (__k__) vai artikkelien (__a__) tageja.

Sinulle näytetään tällöin kaikki tallennetut kirjat tai artikkelit. Anna sen kirjan/artikkelin ID-numero, jonka tageja haluat muokata.

Lisää uusi tagi komennolla __l__. Kirjoita haluamasi tagi ja paina enter.

Poista tagi komennolla __p__. Ohjelma listaa kyseisen kirjan/artikkelin tagit ja niiden ID-numerot. Anna poistettavan tagin ID-numero.

## Vinkin poistaminen

Voit poistaa vinkkejä komennolla __p__. Valitse, haluatko poistaa kirjavinkin (__k__) vai artikkelivinkin (__a__). Anna tämän jälkeen poistettavan vinkin tiedot.
