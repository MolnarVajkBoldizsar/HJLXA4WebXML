<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" encoding="UTF-8" indent="yes"/>
    <xsl:key name="autok-tipus-szerint" match="auto" use="tipus"/>

    <xsl:template match="/">
        <html lang="hu">
            <head><meta charset="UTF-8"/><title>Autótípusok darabszáma</title></head>
            <body>
                <h1>Autótípusok és darabszámuk</h1>
                <table border="1">
                    <tr><th>Autótípus</th><th>Darabszám</th></tr>
                    <xsl:for-each select="/autok/auto[generate-id()=generate-id(key('autok-tipus-szerint', tipus)[1])]">
                        <xsl:sort select="count(key('autok-tipus-szerint', tipus))" data-type="number" order="descending"/>
                        <xsl:sort select="tipus" data-type="text" order="ascending"/>
                        <tr>
                            <td><xsl:value-of select="tipus"/></td>
                            <td><xsl:value-of select="count(key('autok-tipus-szerint', tipus))"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
