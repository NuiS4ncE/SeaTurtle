# Asennus- ja käyttöohje

## Ohjelman käynnistäminen ja käyttäminen

Hae viimeisin julkaistu versio ohjelmasta GitHubin releaseista - linkki löytyy [README.md](https://github.com/NuiS4ncE/SeaTurtle/blob/main/README.md):stä. Mene hakemistoon, johon JAR-tiedoston latasit, ja käynnistä ohjelma komennolla:

`java -jar SeaTurtle-all.jar`

Jos ajettavan JAR-paketin nimi on joku muu, korvaa _SeaTurtle-all.jar_ oikealla tiedostonimellä.

Ohjelman käynnistyessä näytetään käytettävissä olevat komennot. Voit listata käytettävissä olevat komennot kommennolla __h__.

Voit poistua ohjelmasta komennolla __q__.

## Vinkkien listaaminen

### Kirjavinkki

Ohjelmaan talletetut kirjavinkit listataan komennolla __l__.


## Vinkin lisääminen

### Kirjavinkki

Uusi kirjavinkki lisätään komennolla __k__. Ohjelma pyytää syöttämään kirjan nimen, kirjoittajan, sivumäärän ja kirjanmerkin. Kirjan nimi on pakollinen, muut tiedot vapaaehtoisia.

Lisäämisen jälkeen ohjelma listaa kaikki kirjavinkit. Voit lisätä uuden kirjan komennolla __k__ tai palata päävalikkoon komennolla __v__.


### Artikkelivinkki 

Uusi artikkelivinkki lisätään komennolla __a__. Ohjelma pyytää syöttämään artikkelin otsikon ja URL-osoitteen. Artikkelin nimi on pakollinen, mutta URL-osoite on vapaaehtoinen.

Lisäämisen jälkeen ohjelma listaa kaikki artikkelivinkit. Voit lisätä uuden artikkelin komennolla __a__ tai palata päävalikkoon komennolla __v__.


## Kirjavinkin kirjanmerkin lisääminen/muokkaaminen

Kirjavinkille voi lisätä kirjanmerkin tai muokata olemassaolevaa kirjanmerkkiä komenolla __m__. Ohjelma pyytää valitsemaan päivitettävän kirjan numeron listasta ja syöttämään kirjanmerkin sivunumeron. Osuus, joka kirjasta on luettu päivittyy automaattisesti.


## Lukuvinkin hakeminen

Komennolla __e__ voidaan hakea tietty lukuvinkki kirjavinkkien sekä artikkelivinkkien joukosta. Päävalikkoon pääsee painamalla __enter__.

### Kirjavinkki

Hakutulos näyttää kaikki kirjavinkit, joiden nimi sisältää hakutermin. Esimerkiksi hakutermi "tietokon" palauttaa vinkit, joiden nimet sisältävät mm. sanat "tietokoneen", "Tietokoneiden" ja "tietokoneista". 

### Artikkelivinkki

Hakutulos näyttää kaikki artikkelivinkit, joiden otsikko sisältää hakutermin. Hakemisperusteet ovat samat kuin kirjavinkillä. Jos yhtään artikkeliotsikkoa ei löydy, suoritetaan haku uudestaan artikkelien URL-osoitteilla. Hakutermi "f" palauttaa tällöin artikkelivinkit, joiden osoitteet sisältävät merkin "f". Esimerkiksi "https://www.worldwildlife.org/species/sea-turtle" ja "www.hy.fi".

