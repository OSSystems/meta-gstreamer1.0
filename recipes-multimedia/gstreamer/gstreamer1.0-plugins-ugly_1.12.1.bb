require gstreamer1.0-plugins-ugly.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=a6f89e2100d9b6cdffcea4f398e37343 \
                    file://tests/check/elements/xingmux.c;beginline=1;endline=21;md5=4c771b8af188724855cb99cadd390068"

SRC_URI = " \
    http://gstreamer.freedesktop.org/src/gst-plugins-ugly/gst-plugins-ugly-${PV}.tar.xz \
    file://0001-introspection.m4-prefix-pkgconfig-paths-with-PKG_CON.patch \
"
SRC_URI[md5sum] = "30e815834819a4890eb1ef6ef67cf68d"
SRC_URI[sha256sum] = "5e68ba5046e83ee87b17d7a13931e6091466fd771e1338c5b929ee0944d40ad6"

S = "${WORKDIR}/gst-plugins-ugly-${PV}"
