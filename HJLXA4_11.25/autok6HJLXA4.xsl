<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" encoding="UTF-8" indent="yes"/>
    <xsl:key name="autok-varos-szerint" match="auto" use="tulaj/varos"/>

    <xsl:template match="/">
        <html lang="hu">
            <head><meta charset="UTF-8"/><title>Autók városonként</title></head>
            <body>
                <h1>Autók darabszáma és összára városonként</h1>
                <table border="1">
                    <tr><th>Város</th><th>Darabszám</th><th>Összár</th></tr>
                    <xsl:for-each select="/autok/auto[generate-id()=generate-id(key('autok-varos-szerint', tulaj/varos)[1])]">
                        <xsl:sort select="tulaj/varos" data-type="text" order="ascending"/>
                        <tr>
                            <td><xsl:value-of select="tulaj/varos"/></td>
                            <td><xsl:value-of select="count(key('autok-varos-szerint', tulaj/varos))"/></td>
                            <td><xsl:value-of select="sum(key('autok-varos-szerint', tulaj/varos)/ar)"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
