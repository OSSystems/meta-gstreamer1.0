require gstreamer1.0-plugins-ugly.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=a6f89e2100d9b6cdffcea4f398e37343 \
                    file://tests/check/elements/xingmux.c;beginline=1;endline=21;md5=4c771b8af188724855cb99cadd390068"

SRC_URI = " \
    http://gstreamer.freedesktop.org/src/gst-plugins-ugly/gst-plugins-ugly-${PV}.tar.xz \
    file://0001-introspection.m4-prefix-pkgconfig-paths-with-PKG_CON.patch \
"
SRC_URI[md5sum] = "bcb1f8d9339176aee2b5da2a9cb2df88"
SRC_URI[sha256sum] = "3fb9ea5fc8a2de4b3eaec4128d71c6a2d81dd19befe1cd87cb833b98bcb542d1"

S = "${WORKDIR}/gst-plugins-ugly-${PV}"
