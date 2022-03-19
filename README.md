# breviaire.sacy.be : prototype d’application Android

Mettre le contenu du site web "breviaire.sacy.be" dans le dossier d’"assets" avant de générer l’application,
en modifiant les différents `env.js` pour avoir :

- `canonical: 'https://breviaire.sacy.be/.../index.html'` dans `UI_ENV` (en modifiant `...` par la saison concernée) ;

- et toutes les urls qui se terminent par `/index.html`, dans `sites` comme par exemple :

```
    {
        title: 'L’Année Chrétienne - Le Tourneux',
        url: 'https://missel.sacy.be/index.html'
    },
    {
        title: 'Hiver - Bréviaire Le Tourneux',
        url: 'https://breviaire.sacy.be/hiver/index.html'
    },
```