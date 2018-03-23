require gstreamer1.0-plugins-base.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=c54ce9345727175ff66d17b67ff51f58 \
                    file://COPYING.LIB;md5=6762ed442b3822387a51c92d928ead0d \
                    file://common/coverage/coverage-report.pl;beginline=2;endline=17;md5=a4e1830fce078028c8f0974161272607"

SRC_URI = " \
    http://gstreamer.freedesktop.org/src/gst-plugins-base/gst-plugins-base-${PV}.tar.xz \
    file://get-caps-from-src-pad-when-query-caps.patch \
    file://0003-ssaparse-enhance-SSA-text-lines-parsing.patch \
    file://make-gio_unix_2_0-dependency-configurable.patch \
    file://0001-introspection.m4-prefix-pkgconfig-paths-with-PKG_CON.patch \
    file://0005-gstreamer-gl.pc.in-don-t-append-GL_CFLAGS-to-CFLAGS.patch \
    file://0006-glimagesink-Downrank-to-marginal.patch \
    file://0007-Add-lvchostif-to-link-when-using-lEGL-on-rpi.patch \
    file://0008-viv-fb-Include-gstglfuncs.h-to-fix-cross-compilation.patch \
"
SRC_URI[md5sum] = "370271327dd23110421a9c2927ac989a"
SRC_URI[sha256sum] = "7e904660ff56e02b036cf7fdfb77a50a540828ca9d2614d69ba931772e5b6940"

S = "${WORKDIR}/gst-plugins-base-${PV}"
