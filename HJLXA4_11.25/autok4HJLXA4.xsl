<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" encoding="UTF-8" indent="yes"/>

    <xsl:template match="/">
        <html lang="hu">
            <head><meta charset="UTF-8"/><title>Miskolci autók</title></head>
            <body>
                <h1>Miskolci tulajdonosok autóinak rendszámai</h1>
                <ul>
                    <xsl:for-each select="/autok/auto[tulaj/varos='Miskolc']">
                        <li><xsl:value-of select="@rsz"/></li>
                    </xsl:for-each>
                </ul>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
