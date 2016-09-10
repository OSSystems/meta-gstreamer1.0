SUMMARY = "A library on top of GStreamer for building an RTSP server"
HOMEPAGE = "http://cgit.freedesktop.org/gstreamer/gst-rtsp-server/"
SECTION = "multimedia"
LICENSE = "LGPLv2"

DEPENDS = "gstreamer1.0 libcgroup gstreamer1.0-plugins-base"

PNREAL = "gst-rtsp-server"

SRC_URI = "http://gstreamer.freedesktop.org/src/${PNREAL}/${PNREAL}-${PV}.tar.xz"

S = "${WORKDIR}/${PNREAL}-${PV}"

inherit autotools pkgconfig gtk-doc

# Starting with 1.8.0 gst-rtsp-server includes dependency-less plugins as well
LIBV = "1.0"
require gst-plugins-package.inc

FILES_${PN}-dbg += "${libdir}/gstreamer-1.0/.debug"

# gstreamer is not using system-wide makefiles (which we patch in gtkdoc recipe,
# but its own custom ones, which we have to patch here
patch_gtk_doc_makefiles() {
        # Patch the gtk-doc makefiles so that the qemu wrapper is used to run transient binaries
        # instead of libtool wrapper or running them directly
        sed -i \
           -e "s|GTKDOC_RUN =.*|GTKDOC_RUN = \$(top_builddir)/gtkdoc-qemuwrapper|" \
           ${S}/common/gtk-doc*mak
}

do_configure[prefuncs] += " patch_gtk_doc_makefiles"

