<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" encoding="UTF-8" indent="yes"/>

    <xsl:template match="/">
        <html lang="hu">
            <head>
                <meta charset="UTF-8"/>
                <title>HJLXA4 Órarend – 2026. I. félév</title>
                <style>
                    body { margin: 24px; font-family: Arial, sans-serif; color: #233447; }
                    h1 { text-align: center; color: #294d70; font-size: 22px; }
                    table { width: 100%; border-collapse: collapse; text-align: center; }
                    th { padding: 9px 5px; background: #294d70; color: white; }
                    td { padding: 8px 5px; border: 1px solid #b7c3cf; }
                    tr:nth-child(even) { background: #e7f0f8; }
                    tr:nth-child(odd) { background: #d3e2f1; }
                </style>
            </head>
            <body>
                <h1>HJLXA4 Órarend – 2026. I. félév</h1>
                <table>
                    <tr>
                        <th>ID</th>
                        <th>Tantárgy</th>
                        <th>Típus</th>
                        <th>Nap</th>
                        <th>Kezdés</th>
                        <th>Befejezés</th>
                        <th>Helyszín</th>
                        <th>Oktató</th>
                        <th>Szak</th>
                    </tr>
                    <xsl:for-each select="MVB_orarend/ora">
                        <tr>
                            <td><xsl:value-of select="@id"/></td>
                            <td><xsl:value-of select="targy"/></td>
                            <td>
                                <xsl:choose>
                                    <xsl:when test="@tipus='eloadas'">előadás</xsl:when>
                                    <xsl:otherwise><xsl:value-of select="@tipus"/></xsl:otherwise>
                                </xsl:choose>
                            </td>
                            <td>
                                <xsl:choose>
                                    <xsl:when test="idopont/nap='Hetfo'">Hétfő</xsl:when>
                                    <xsl:when test="idopont/nap='Kedd'">Kedd</xsl:when>
                                    <xsl:when test="idopont/nap='Szerda'">Szerda</xsl:when>
                                    <xsl:otherwise><xsl:value-of select="idopont/nap"/></xsl:otherwise>
                                </xsl:choose>
                            </td>
                            <td><xsl:value-of select="idopont/tol"/></td>
                            <td><xsl:value-of select="idopont/ig"/></td>
                            <td><xsl:value-of select="helyszin"/></td>
                            <td><xsl:value-of select="oktato"/></td>
                            <td><xsl:value-of select="szak"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
