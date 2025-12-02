import os
import pandas as pd
import xml.etree.ElementTree as ET
from typing import List, Optional, Callable, Iterable


def load_dataset(path: str, dtype=str) -> pd.DataFrame:
    """Load CSV or Excel into a pandas DataFrame. Raises FileNotFoundError if missing."""
    if path is None:
        raise ValueError("path is required")
    if not os.path.exists(path):
        raise FileNotFoundError(f"Dataset file not found: {path}")
    if path.lower().endswith(('.xls', '.xlsx')):
        return pd.read_excel(path, dtype=dtype)
    return pd.read_csv(path, dtype=dtype)


def ensure_columns(df: pd.DataFrame, required: List[str], fill_value=None) -> pd.DataFrame:
    """Ensure the DataFrame contains all required columns. Returns a copy with columns ordered as `required`."""
    for col in required:
        if col not in df.columns:
            df[col] = fill_value
    return df[required].copy()


def coerce_numeric(df: pd.DataFrame, cols: Iterable[str]):
    """Coerce listed columns to numeric (in-place)."""
    for c in cols:
        if c in df.columns:
            df[c] = pd.to_numeric(df[c], errors='coerce')


def coerce_datetime(df: pd.DataFrame, cols: Iterable[str]):
    """Coerce listed columns to datetime (in-place)."""
    for c in cols:
        if c in df.columns:
            df[c] = pd.to_datetime(df[c], errors='coerce')


def safe_indent(elem: ET.Element, space: str = "  ", level: int = 0):
    """Call ET.indent when available (Python 3.9+), otherwise do nothing."""
    try:
        ET.indent(elem, space=space, level=level)
    except Exception:
        return


def dataframe_to_xml(df: pd.DataFrame, out_path: str, root_name: str = "Reports", item_name: str = "Report",
                     id_generator: Optional[Callable[[], Iterable]] = None,
                     tag_normalizer: Optional[Callable[[str], str]] = None):
    """Write DataFrame to XML. Accepts optional id generator and tag normalizer.

    - id_generator: callable returning an iterator of ids (e.g., lambda: iter(range(1000000))).
    - tag_normalizer: callable to transform column names into XML tags.
    """
    if df is None or df.empty:
        raise ValueError("No data available to write XML report.")

    root = ET.Element(root_name)

    if id_generator is None:
        gen = (i for i in range(len(df)))
    else:
        gen = id_generator()

    for _, row in df.iterrows():
        rid = str(next(gen))
        report_elem = ET.SubElement(root, item_name, id=rid)
        for col in df.columns:
            tag = tag_normalizer(col) if tag_normalizer else str(col)
            val = row[col]
            text = "" if pd.isna(val) else str(val)
            ET.SubElement(report_elem, tag).text = text

    safe_indent(root)

    tree = ET.ElementTree(root)
    # Ensure parent folder exists
    out_dir = os.path.dirname(out_path)
    if out_dir:
        os.makedirs(out_dir, exist_ok=True)

    with open(out_path, 'wb') as f:
        tree.write(f, encoding='utf-8', xml_declaration=True, short_empty_elements=False)


def sequential_id_generator(start: int = 0):
    def gen():
        i = start
        while True:
            yield i
            i += 1
    return gen
