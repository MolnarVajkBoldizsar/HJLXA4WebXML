<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" encoding="UTF-8" indent="yes"/>

    <xsl:template match="/">
        <html lang="hu">
            <head><meta charset="UTF-8"/><title>Autok rendszama es ara</title></head>
            <body>
                <h1>Autók rendszáma és ára – ár szerint növekvő sorrend</h1>
                <table border="1">
                    <tr><th>Rendszam</th><th>Ar</th></tr>
                    <xsl:for-each select="/autok/auto">
                        <xsl:sort select="ar" data-type="number" order="ascending"/>
                        <tr>
                            <td><xsl:value-of select="@rsz"/></td>
                            <td><xsl:value-of select="ar"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
