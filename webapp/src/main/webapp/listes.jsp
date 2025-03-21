<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Listes des joueurs</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div class="row">
        <div class="col">
            <h1>Liste des femmes</h1>
            <table>
                <c:forEach var = "joueur" items="${listeFemmes}">
                    <tr>
                        <td>${joueur.prenom}</td>
                        <td>${joueur.nom.toUpperCase()}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div class="col">
            <h1>Liste des hommes</h1>
            <table>
                <c:forEach var = "joueur" items="${listeHommes}">
                    <tr>
                        <td>${joueur.prenom}</td>
                        <td>${joueur.nom.toUpperCase()}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</body>
</html>