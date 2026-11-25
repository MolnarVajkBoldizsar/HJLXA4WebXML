<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" encoding="UTF-8" indent="yes"/>

    <xsl:template match="/">
        <html lang="hu">
            <head><meta charset="UTF-8"/><title>Drágább autók száma</title></head>
            <body>
                <h1>30000-nél drágább autók száma</h1>
                <p><xsl:value-of select="count(/autok/auto[ar &gt; 30000])"/></p>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
