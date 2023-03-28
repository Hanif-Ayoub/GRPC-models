<h3>Tester le serveur avec un client GRPC "BloomRPC"</h3>
  <h4>Unary Model</h4>
<p>Le premier modèle, Unary, implique une simple requête-réponse entre le client et le serveur pour convertir une valeur de devise unique.</p>
  <img src="Captures/1.png">
  <h4>Server Sreaming Model</h4>
<p>Le deuxième modèle, Server Streaming, permet au serveur de renvoyer un flux continu de résultats de conversion à mesure que la valeur de la devise change.</p>
  <img src="Captures/2.png">
  <h4>Client Streaming Model</h4>
<p>Le troisième modèle, Client Streaming, permet au client d'envoyer un flux continu de valeurs de devise à convertir et le serveur renvoie une seule réponse.</p>
  <img src="Captures/3.png">
  <h4>BiDirectional Streaming Model</h4>
<p>Le quatrième modèle, BiDirectional Streaming, permet une communication bidirectionnelle entre le client et le serveur, où le client envoie un flux continu de valeurs de devise à convertir et le serveur renvoie un flux continu de résultats de conversion.</p>
  <img src="Captures/4.png">
