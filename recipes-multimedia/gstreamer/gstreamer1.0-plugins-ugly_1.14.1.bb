require gstreamer1.0-plugins-ugly.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=a6f89e2100d9b6cdffcea4f398e37343 \
                    file://tests/check/elements/xingmux.c;beginline=1;endline=21;md5=4c771b8af188724855cb99cadd390068"

SRC_URI = " \
    http://gstreamer.freedesktop.org/src/gst-plugins-ugly/gst-plugins-ugly-${PV}.tar.xz \
    file://0001-introspection.m4-prefix-pkgconfig-paths-with-PKG_CON.patch \
"
SRC_URI[md5sum] = "d1563f5dc0508afc6f8b261324d765d9"
SRC_URI[sha256sum] = "cff2430bb13f54ef81409a0b3d65ce409a376d4a7bab57a14a97d602539fe1d3"

S = "${WORKDIR}/gst-plugins-ugly-${PV}"
