require gstreamer1.0-plugins-good.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=a6f89e2100d9b6cdffcea4f398e37343 \
                    file://common/coverage/coverage-report.pl;beginline=2;endline=17;md5=a4e1830fce078028c8f0974161272607 \
                    file://gst/replaygain/rganalysis.c;beginline=1;endline=23;md5=b60ebefd5b2f5a8e0cab6bfee391a5fe"

SRC_URI = " \
    http://gstreamer.freedesktop.org/src/gst-plugins-good/gst-plugins-good-${PV}.tar.xz \
    file://0001-gstrtpmp4gpay-set-dafault-value-for-MPEG4-without-co.patch \
    file://avoid-including-sys-poll.h-directly.patch \
    file://ensure-valid-sentinel-for-gst_structure_get.patch \
    file://0001-introspection.m4-prefix-pkgconfig-paths-with-PKG_CON.patch \
    file://0002-qt-Get-EGL-native-display-from-QPA-if-platform-heade.patch \
"
SRC_URI[md5sum] = "48584b02e469a314b0e71f1553a2d2a7"
SRC_URI[sha256sum] = "6afa35747d528d3ab4ed8f5eac13f7235d7d28100d6a24dd78f81ec7c0d04688"

S = "${WORKDIR}/gst-plugins-good-${PV}"

RPROVIDES_${PN}-pulseaudio += "${PN}-pulse"
RPROVIDES_${PN}-soup += "${PN}-souphttpsrc"
