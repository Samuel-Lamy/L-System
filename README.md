# L-System
## Présentation du projet
- Ce projet à été fait en **Java** dans le cours de structure de donnée qui simule le développement de plantes et d’autres formes.
- L'application .jar prend dans la ligne de commande un fichier JSON et un nombre d'itérations (int) et met l'output dans un fichier .eps spécifié.
### Exemple
```json
{
	"alphabet" : 	["F", "[", "]", "+", "-"],
	"rules" : {
		"F" : ["FF-[-F+F+F]+[+F-F-F]"]
	},
	"axiom":"F",
	"actions": {
		"F":"draw", 
		"[":"push",
		"]":"pop", 
		"+":"turnL", 
		"-":"turnR"},
	"parameters" : {"step": 3, "angle":22.5, "start":[250,0,90]}
}
```
Avec 5 itération output:
<br />
![arbre_lsystem](https://user-images.githubusercontent.com/76920716/150235723-2eb24ac7-eeee-4f03-9a35-1c703de3334d.PNG)
