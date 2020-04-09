<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Currency Converter</title>
    <link rel="stylesheet" href="/bulma.min.css">
    <style>
        .header {
            display: grid;
            grid-template-columns: auto;
            grid-template-rows: 100px min-content;
        }

        .header * {
            align-self: center;
            justify-self: center;
        }

        .grid {
            display: grid;
            grid-template-rows: min-content min-content 75px;
            grid-template-columns: auto 200px auto;
        }

        .grid * {
            align-self: center;
            justify-self: center;
        }
    </style>
</head>
<body>
    <div class="header">
        <h1 class="title is-3">Currency Converter</h1>
        <div class="field">
            <p class="control">
                <a href="/history" class="button is-small">Previous Conversions</a>
            </p>
        </div>
    </div>

    <form class="grid" action="/convert" method="POST">
        <label for="fromCurrency">From</label>
        <label for="toCurrency" style="grid-column-start: 3">To</label>

        <div class="select is-medium">
            <select name="fromCurrency" id="fromCurrency" required>
                <option value="" disabled selected>???</option>
                <c:forEach var="cL" items="${cList}">
                    <option value="${cL}">${cL}</option>
                </c:forEach>
            </select>
        </div>

        <input class="input" name="fromAmt" id="fromAmt" type="number" step="0.01" min="0.0" required>


        <div class="select is-medium">
            <select name="toCurrency" id="toCurrency" required>
                <option value="" disabled selected>???</option>
                <c:forEach var="cL" items="${cList}">
                    <option value="${cL}">${cL}</option>
                </c:forEach>
            </select>
        </div>

        <button class="button" id="convert" style="grid-column-start: 2">Convert</button>
    </form>
</body>
</html>